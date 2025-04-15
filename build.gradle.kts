// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("com.android.application") version "8.0.0" apply false // Ganti dengan versi yang sesuai
}
    buildscript {
        repositories {
            google()
            mavenCentral()
        }
        tasks.register<Delete>("clean") {
            delete(rootProject.buildDir)
        }
    }
