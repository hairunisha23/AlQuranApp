plugins {
    // Tidak perlu plugin di level ini biasanya
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.8.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.10")
    }
    repositories {
        google()
        mavenCentral()
    }
}
