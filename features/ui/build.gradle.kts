plugins {
    id("com.android.library") //alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.ksp)
}
//plugins {
//    alias(libs.plugins.android.application) apply false
//    id("com.android.library") //alias(libs.plugins.android.library) apply false
//    alias(libs.plugins.kotlin.android) apply false
//}



//apply plugin: 'com.google.gms.google-services'
//apply from: "D:\\walhalla\\sdk\\android\\ui\\ui/versions.gradle"


/*
android.nonTransitiveRClass=false
android.defaults.buildfeatures.aidl=true
*
*/



android {
    namespace = "com.walhalla.ui"
    compileSdk = libs.versions.compileSdk.get().toInt()
    buildToolsVersion = libs.versions.buildTools.get()

    defaultConfig {
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        debug {
            // minifyEnabled не используется в библиотеке
        }
        release {
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
            consumerProguardFiles("consumer-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    implementation(libs.androidx.constraintlayout)

//    implementation "androidx.appcompat:appcompat${compatVersion}:"
//    implementation "com.google.android.material:material:${materialVersion}"


    api(libs.androidx.preference)

    implementation(libs.androidx.swiperefreshlayout)

    implementation(libs.androidx.annotation)
    implementation(libs.jsoup)
    //implementation 'com.squareup.okhttp3:okhttp:5.0.0-alpha.2'
    //noinspection GradleDependency
    //implementation("com.squareup.okhttp3:okhttp:$rootProject.okHttpVersion")
    //NOT USED=> Firebase Crashlytics
    //NOT USED=> implementation "com.google.firebase:firebase-crashlytics:$rootProject.crashlyticsVersion"
    //NOT USED=> implementation "com.google.firebase:firebase-analytics:$rootProject.analyticsVersion"

    //NOT USED=> implementation("com.google.firebase:firebase-ads:$rootProject.gmsAds")
    //implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.21"
    //implementation 'androidx.multidex:multidex:2.0.1'


    //@@@ implementation@@@@@@@@@@@@@@@core:1.10.3'//Google Play In-App Review API
    implementation(libs.app.update)
    // For Kotlin users, also add the Kotlin extensions library for Play In-App Update:
    implementation(libs.app.update.ktx)

    //Google Play In-App Review API
    implementation(libs.review)
    // For Kotlin users, also add the Kotlin extensions library for Play In-App Review:
    implementation(libs.review.ktx)
}