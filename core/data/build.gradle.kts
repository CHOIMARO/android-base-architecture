plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    // Hilt
    alias(libs.plugins.android.hilt)

    // ksp
    alias(libs.plugins.kotlin.ksp)
}

android {
    namespace = "com.company.data"
    compileSdk {
        version = release(36)
    }

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core:domain"))
    implementation(project(":core:network"))
    implementation(project(":core:datastore"))
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler) // 또는 kapt(libs.hilt.compiler)

    // Http
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.okhttp.logging.interceptor) // 네트워크 통신을 로깅하기 위해 (강력 추천)
    implementation(libs.moshi.core)
    ksp(libs.moshi.kotlin.codegen)

    // Paging
    implementation(libs.androidx.paging.runtime)
}