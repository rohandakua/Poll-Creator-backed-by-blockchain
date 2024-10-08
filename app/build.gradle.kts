plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)

    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.google.gms.google.services)



}

android {
    namespace = "com.example.pollcreator"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.pollcreator"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17  // changed
        targetCompatibility = JavaVersion.VERSION_17  // changed
    }
    kotlinOptions {
        jvmTarget = "17"       // changed
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    packaging {                                     // changed
        resources {
            excludes += "/META-INF/DISCLAIMER"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    // Import the BoM for the Firebase platform
    implementation(platform("com.google.firebase:firebase-bom:33.1.2"))

    implementation("com.google.firebase:firebase-auth")

    implementation("com.google.dagger:hilt-android:2.44")
    implementation(libs.firebase.auth)
    implementation(libs.firebase.database)
    implementation(libs.androidx.appcompat)
    kapt("com.google.dagger:hilt-android-compiler:2.44")
    // FirebaseUI for Firebase Auth
    implementation ("com.firebaseui:firebase-ui-auth:8.0.2")


    implementation ("com.airbnb.android:lottie-compose:6.0.0")
    implementation(libs.androidx.navigation.compose)

    //retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")




    // web3j
    implementation ("org.web3j:core:4.12.1")


    //walletConnect

    //webview
    implementation ("androidx.webkit:webkit:1.5.0")



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

}