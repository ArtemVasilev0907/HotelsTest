@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.skydivers.hotelstest.features.booking.ui"
    compileSdk = ProjectProps.compileSdk

    defaultConfig {
        minSdk = ProjectProps.minSdk

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
}

dependencies {

    implementation(project(":core"))
    implementation(project(":core:theme"))


    implementation(project(":features:booking:domain"))
    implementation(project(":features:booking:model"))
    implementation(project(":features:booking:data"))


    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(Deps.kotlin_reflect)
    implementation(Deps.kotlinx_serialization)


    implementation(libs.bundles.lifecycle)
    implementation(libs.bundles.koin)




    testImplementation(libs.junit4)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
}