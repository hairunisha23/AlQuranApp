// build.gradle.kts (di dalam folder app)
plugins {
    id("com.android.application") version "8.0.0" apply false
    kotlin("android") version "1.7.10" apply false}

android {
    compileSdk = 33 // Versi SDK yang digunakan

    defaultConfig {
        applicationId = "com.example.alquranapp" // Ganti dengan ID aplikasi Anda
        minSdk = 21 // Versi minimum SDK yang didukung
        targetSdk = 33 // Versi target SDK
        versionCode = 1 // Kode versi aplikasi
        versionName = "1.0" // Nama versi aplikasi
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false // Menonaktifkan ProGuard untuk rilis
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro") // File ProGuard
        }
    }
}

dependencies {
    implementation("com.squareup.retrofit2:retrofit:2.9.0") // Dependensi Retrofit
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") // Konverter Gson untuk Retrofit
    implementation("androidx.appcompat:appcompat:1.4.1") // Dependensi AppCompat
    implementation("com.google.android.material:material:1.5.0") // Dependensi Material Design
    implementation("androidx.constraintlayout:constraintlayout:2.1.3") // Dependensi ConstraintLayout
}