// apply plugin: 'com.android.library'

// android {
//     compileSdkVersion 29
//     buildToolsVersion "29.0.2"

//     defaultConfig {
//         minSdkVersion 19
//         targetSdkVersion 29
//         versionCode 1
//         versionName "1.0"

//         testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
//         consumerProguardFiles 'consumer-rules.pro'
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
//     testImplementation 'junit:junit:4.12'
//     androidTestImplementation 'androidx.test.ext:junit:1.1.1'
//     androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'
// }

plugins {
    alias(libs.plugins.android.library)
}

android {
    namespace = "com.viper.android.render"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}