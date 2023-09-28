plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.skydivers.hotelstest.booking"
    compileSdk = ProjectProps.compileSdk

    defaultConfig {
        minSdk = ProjectProps.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
//        consumerProguardFiles("consumer-rules.pro")
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

    buildToolsVersion = ProjectProps.buildToolsVersion
}

dependencies {



    api(project(":booking:ui"))
    implementation (project(":app"))


    implementation(libs.androidx.core.ktx)



    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}