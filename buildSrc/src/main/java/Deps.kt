object Verison{
    const val appcompat_version = "1.6.1"

    //kotlin core
    const val coreKtx_version = "1.12.0"

    //kotlin activity
    const val activityKtx_version = "1.7.2"

    //Material
    const val material_version = "1.9.0"

    //Lifrcycle
    const val lifecycle_version = "2.6.2"

    //Coroutines
    const val coroutine_version ="1.7.3"

    //Serialization
    const val serialization_version ="1.6.0"

    //navigation
    const val nav_version = "2.7.2"

    // Coil
    const val coil_version = "2.4.0"

    //Retrofit
    const val retrofit_version = "2.9.0"

    //koin for kotlin
    const val koin_version = "3.5.0"
}
object Deps{
    const val androidPlugin = "com.android.tools.build:gradle:8.1.1"

    const val appCompat = "androidx.appcompat:appcompat:${Verison.appcompat_version}"

    const val coreKtx = "androidx.core:core-ktx:${Verison.coreKtx_version}"

    const val activityKtx = "androidx.activity:activity-ktx:${Verison.activityKtx_version}"

    const val androidx_datastore = "androidx.datastore:datastore-core:1.0.0"

    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:1.9.0"

    const val material = "com.google.android.material:material:${Verison.material_version}"

    const val kotlinx_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Verison.coroutine_version}"

    const val kotlinx_serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Verison.serialization_version}"


    val androidx_swiperefreshlayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

    // Coil
    val coil = "io.coil-kt:coil:${Verison.coil_version}"

    //Google play service
    var play_location = "com.google.android.gms:play-services-location:21.0.1"


    var junit = "junit:junit:4.13.2"
}

object LifecycleImplementation{

    const val ktx_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Verison.lifecycle_version}"

    const val ktx_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Verison.lifecycle_version}"

    const val androidx_lifecycle_ext = "androidx.lifecycle:lifecycle-extensions:2.2.0"
}

object KoinImplemetation{
    //koin for kotlin
    var koin_core = "io.insert-koin:koin-core:${Verison.koin_version}"
    var koin_android = "io.insert-koin:koin-android:${Verison.koin_version}"
    //koin testing
    var koin_test = "io.insert-koin:koin-test:${Verison.koin_version}"
}

object NavigationImplementation{
    //Navigation fragment
    const val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Verison.nav_version}"
    //Navigation ui
    const val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Verison.nav_version}"
}

object RetrofitImplementation{
    //Retrofit
    val retrofit = "com.squareup.retrofit2:retrofit:${Verison.retrofit_version}"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:${Verison.retrofit_version}"
    val okhttp3_logging = "com.squareup.okhttp3:logging-interceptor:4.10.0"
}