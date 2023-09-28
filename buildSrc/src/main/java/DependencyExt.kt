import org.gradle.api.artifacts.dsl.DependencyHandler

fun DependencyHandler.implementation(dependency:String){

    add("implementation", dependency)
}

fun DependencyHandler.androidTest(dependency:String){

    add("androidTest", dependency)
}

fun DependencyHandler.debugImplementation(dependency: String){

    add("debugimplementation", dependency)
}