package plugins

import ProjectPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import plugins.util.getLibsVersionCatalog
import plugins.util.implementation
import plugins.util.kapt

class AndroidHiltPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(ProjectPlugins.HILT)
                apply(ProjectPlugins.KAPT)
            }

            val libs = getLibsVersionCatalog()
            dependencies {
                implementation(libs.findLibrary("hilt.android").get())
                kapt(libs.findLibrary("hilt.android.compiler").get())
            }
        }
    }
}