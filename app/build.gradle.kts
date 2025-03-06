import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp") version "2.0.0-1.0.24"
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.cosmicstruck.linkspehere"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cosmicstruck.linkspehere"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val file = project.rootProject.file("secret.properties")
        val properties = Properties()
        properties.load(file.inputStream())
        val STREAM_API_KEY = properties.getProperty("STREAM_SDK_API")?: ""
        val STREAM_SECRET_KEY = properties.getProperty("STREAM_SDK_SECRET")?: ""
        buildConfigField("String","STREAM_SDK_API",STREAM_API_KEY)
        buildConfigField("String","STREAM_SDK_SECRET",STREAM_SECRET_KEY)
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.52")
    ksp("com.google.dagger:hilt-android-compiler:2.52")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    //Navigation
    implementation("androidx.navigation:navigation-compose:2.8.1")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    //Room
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-paging:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    //Coil
    implementation("io.coil-kt.coil3:coil-compose:3.0.4")
    implementation("io.coil-kt.coil3:coil-network-okhttp:3.0.4")

    //SplashScreen
    implementation("androidx.core:core-splashscreen:1.0.1")

//Swipable
    implementation("me.saket.swipe:swipe:1.3.0")

//Paging
    implementation("androidx.paging:paging-compose:3.3.4")

//Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

//WorkManger
    implementation("androidx.work:work-runtime-ktx:2.10.0")

//Accomapnist Notifications
    implementation("com.google.accompanist:accompanist-permissions:0.37.0")

//DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.1")


//Permission
    implementation("com.google.accompanist:accompanist-permissions:0.37.0")

    implementation("androidx.compose.ui:ui-text-google-fonts:1.7.8")
    val streamChat = "6.12.0"
    implementation("io.getstream:stream-chat-android-offline:$streamChat")
    implementation("io.getstream:stream-chat-android-compose:$streamChat")

    implementation("com.brendangoldberg:kotlin-jwt:1.3.1")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}