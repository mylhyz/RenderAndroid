// apply plugin: 'com.android.application'

// android {
//     compileSdkVersion 29
//     buildToolsVersion "29.0.2"

//     defaultConfig {
//         applicationId "com.viper.android.render.sample"
//         minSdkVersion 19
//         targetSdkVersion 29
//         versionCode 1
//         versionName "1.0"

//         testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//     }

//     buildTypes {
//         release {
//             minifyEnabled false
//             proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
//         }
//     }

// }

// dependencies {
//     implementation fileTree(dir: 'libs', include: ['*.jar'])

//     implementation 'androidx.appcompat:appcompat:1.1.0'
//     implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
//     testImplementation 'junit:junit:4.12'
//     androidTestImplementation 'androidx.test.ext:junit:1.1.1'
//     androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
//     implementation project(path: ':render')
//     implementation project(path: ':browser')
// }


plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}