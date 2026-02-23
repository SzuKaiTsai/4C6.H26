plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ca.qc.cstj.inkify"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "ca.qc.cstj.inkify"
        minSdk = 26
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    //Utilities
    implementation(libs.androidx.material.icons.extended)
    implementation(libs.androidx.compose.ui.text.google.fonts)

    //Lifecycle et navigation
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.navigation3.runtime)
    implementation(libs.androidx.navigation3.ui)

    //Room
    //implementation("androidx.room:room-runtime:2.8.4")
    implementation(libs.androidx.room.runtime)
    //ksp("androidx.room:room-compiler:2.8.4")
    ksp(libs.androidx.room.compiler)

    //Datastore
    //implementation("androidx.datastore:datastore-preferences:1.2.0")
    implementation(libs.androidx.datastore.preferences)

    //Nouvelles bibiothèques
    //implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.7.1")
    implementation(libs.kotlinx.datetime)
    //implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.10.0") et plugin
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}