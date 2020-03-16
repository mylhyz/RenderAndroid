# 相关材料

### chromium android 中沙盒进程创建的方式

https://sourcegraph.com/github.com/chromium/chromium@36d16d147f2878127ab125f865444f894ccf18ef/-/blob/weblayer/public/java/org/chromium/weblayer/ChildProcessService.java#L21:23

大体含义是，创建多个 Service 类提前注册，分别命名为sandbox，
然后对于更细节的子进程逻辑实现是通过反射获取的内核代码

通过下面的demo可以看出，chromium的子进程数最大是多少

```java
/**
 * Delegates service calls to the chrome service implementation.
 */
@SuppressWarnings("JavadocType")
public abstract class ChildProcessService extends Service {
    private IChildProcessService mImpl;

    public ChildProcessService() {}

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            Context appContext = getApplicationContext();
            Context remoteContext = WebLayer.getOrCreateRemoteContext(appContext);
            if (WebLayer.getSupportedMajorVersion(appContext) < 81) {
                mImpl = IChildProcessService.Stub.asInterface(
                        (IBinder) remoteContext.getClassLoader()
                                .loadClass("org.chromium.weblayer_private.ChildProcessServiceImpl")
                                .getMethod("create", Service.class, Context.class)
                                .invoke(null, this, appContext));
            } else {
                mImpl = IChildProcessService.Stub.asInterface(
                        (IBinder) remoteContext.getClassLoader()
                                .loadClass("org.chromium.weblayer_private.ChildProcessServiceImpl")
                                .getMethod("create", Service.class, Context.class, Context.class)
                                .invoke(null, this, appContext, remoteContext));
            }
            mImpl.onCreate();
        } catch (Exception e) {
            throw new APICallException(e);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            mImpl.onDestroy();
            mImpl = null;
        } catch (RemoteException e) {
            throw new APICallException(e);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        try {
            return ObjectWrapper.unwrap(mImpl.onBind(ObjectWrapper.wrap(intent)), IBinder.class);
        } catch (RemoteException e) {
            throw new APICallException(e);
        }
    }

    public static class Privileged extends ChildProcessService {}
    public static final class Privileged0 extends Privileged {}
    public static final class Privileged1 extends Privileged {}
    public static final class Privileged2 extends Privileged {}
    public static final class Privileged3 extends Privileged {}
    public static final class Privileged4 extends Privileged {}

    public static class Sandboxed extends ChildProcessService {}
    public static final class Sandboxed0 extends Sandboxed {}
    // ... 省略大部分模板代码
    public static final class Sandboxed39 extends Sandboxed {}
}
```