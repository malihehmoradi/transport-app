plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.diffplug.spotless") version "7.0.3"

    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.example.barflowapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.barflowapp"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
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
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)

    implementation(libs.gson)

    // Hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.ui.test.junit4.android)
    implementation(libs.androidx.runner)
    ksp(libs.hilt.android.compiler)

    // Testing dependencies
    // Core KTX for testing utilities
    testImplementation(libs.androidx.core.ktx) // Already there, but good to note

    // JUnit for unit testing
    testImplementation(libs.junit) // Standard unit testing framework
    androidTestImplementation(libs.androidx.junit) // For Android instrumentation tests

    // Truth for assertions (more readable than JUnit's built-in assertions)
    testImplementation(libs.truth) // Or latest version
    androidTestImplementation(libs.truth)

    // Mockito for mocking dependencies in unit tests
    testImplementation(libs.mockito.kotlin) // Or latest version
    testImplementation(libs.mockito.inline) // For mocking final classes/methods if needed

    // Coroutines Test Utilities
    testImplementation(libs.kotlinx.coroutines.test) // Or latest version matching your coroutines version

    // Hilt Testing Utilities
    testImplementation("com.google.dagger:hilt-android-testing:2.56.2")
    androidTestImplementation("com.google.dagger:hilt-android-testing:2.56.2")
//    testImplementation(libs.hilt.android.compiler) // For unit testing with Hilt
    androidTestImplementation(libs.hilt.android.compiler) // For instrumentation testing with Hilt
    kspTest(libs.hilt.android.compiler) // For Hilt's test code generation (if needed for unit tests)
    kspAndroidTest(libs.hilt.android.compiler) // For Hilt's test code generation in Android tests

    // Compose UI Testing
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4) // Core Compose testing library
    debugImplementation(libs.androidx.ui.tooling) // For @Preview and inspection
    debugImplementation(libs.androidx.ui.test.manifest) // For test manifest

    // Espresso for some UI interactions (though Compose test APIs are preferred for Compose)
    androidTestImplementation(libs.androidx.espresso.core)
}

spotless {
    kotlin {
        target("**/*.kt", "**/*.kts")
        targetExclude("**/build/**/*.kt", "**/build/**/*.kts", "**/bin/**/*.kt", "buildSrc/**/*.kts")
        ktlint()
    }
}