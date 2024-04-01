package plugins

import ProjectPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.project
import plugins.util.*
import plugins.util.androidTestImplementation
import plugins.util.debugImplementation
import plugins.util.implementation

class AndroidFeaturePlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply {
                apply(ProjectPlugins.PAP_ANDROID_LIBRARY)
                apply(ProjectPlugins.PAP_ANDROID_COMPOSE)
                apply(ProjectPlugins.PAP_ANDROID_HILT)
            }

            val libs = getLibsVersionCatalog()
            dependencies {
                test(libs)
                androidTest(libs)
                implementation(project(":core:domain"))
                implementation(project(":core:model"))
                implementation(project(":core:common"))
                implementation(project(":ui:components"))
                implementation(libs.findLibrary("voyager.navigator").get())
                implementation(libs.findLibrary("voyager.transitions").get())
                implementation(libs.findLibrary("voyager.tab.navigator").get())
                implementation(libs.findLibrary("tabler.icons").get())
                implementation(libs.findLibrary("androidx.lifecycle.runtime").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel").get())
                implementation(libs.findLibrary("androidx.lifecycle.viewmodel.compose").get())
                implementation(libs.findLibrary("coroutines.android").get())

                debugImplementation(libs.findLibrary("androidx.compose.ui.testManifest").get())

                androidTestImplementation(libs.findLibrary("androidx.compose.ui.test").get())
            }
        }
    }
}