@file:Suppress("MissingPackageDeclaration", "StringLiteralDuplication")

package plugins.util

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.project

fun Project.getLibsVersionCatalog(): VersionCatalog =
    extensions.getByType<VersionCatalogsExtension>().named("libs")

fun DependencyHandler.test(libs: VersionCatalog) {

    testImplementation(libs.findLibrary("junit").get())
    testImplementation(libs.findLibrary("coroutines.test").get())
}

fun DependencyHandler.androidTest(libs: VersionCatalog) {
    androidTestImplementation(libs.findLibrary("androidx.test.ext.junit").get())
    androidTestImplementation(libs.findLibrary("androidx.test.core").get())
    androidTestImplementation(libs.findLibrary("coroutines.test").get())
}

internal fun DependencyHandler.implementation(dependencyNotation: Any) {
    add("implementation", dependencyNotation)
}

internal fun DependencyHandler.debugImplementation(dependencyNotation: Any) {
    add("debugImplementation", dependencyNotation)
}

internal fun DependencyHandler.kapt(dependencyNotation: Any) {
    add("kapt", dependencyNotation)
}

internal fun DependencyHandler.ksp(dependencyNotation: Any) {
    add("ksp", dependencyNotation)
}

internal fun DependencyHandler.api(dependencyNotation: Any) {
    add("api", dependencyNotation)
}

fun DependencyHandler.coil(libs: VersionCatalog) {
    implementation(platform(libs.findLibrary("coil.bom").get()))
    implementation(libs.findLibrary("coil.base").get())
    implementation(libs.findLibrary("coil.compose.base").get())
    implementation(libs.findLibrary("coil.svg").get())
}

internal fun DependencyHandler.testImplementation(dependencyNotation: Any) {
    add("testImplementation", dependencyNotation)
}

internal fun DependencyHandler.androidTestImplementation(dependencyNotation: Any) {
    add("androidTestImplementation", dependencyNotation)
}