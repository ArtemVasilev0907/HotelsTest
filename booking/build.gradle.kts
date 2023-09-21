plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.skydivers.hotelstest.booking"
    compileSdk = 34

    defaultConfig {
        minSdk = 23


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
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true

    }

    dataBinding {

        this.enable = true

    }
    buildToolsVersion = "33.0.1"
}

dependencies {

    implementation(Deps.coreKtx)
    implementation(Deps.appCompat)
    implementation(Deps.material)
    implementation(Deps.kotlin_reflect)
    implementation(Deps.kotlinx_serialization)
    implementation(Deps.ktx_livedata)
    implementation(Deps.androidx_lifecycle_ext)
    implementation(Deps.ktx_viewmodel)
    implementation(Deps.retrofit)
    implementation(Deps.retrofit_gson)
    implementation(Deps.okhttp3_logging)
    implementation(Deps.koin_core)
    implementation(Deps.koin_android)
    implementation(Deps.koin_test)
    implementation(project(mapOf("path" to ":domain")))
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}