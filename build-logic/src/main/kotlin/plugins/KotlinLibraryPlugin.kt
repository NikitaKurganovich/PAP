package plugins

import ProjectPlugins
import org.gradle.api.Plugin
import org.gradle.api.Project
import plugins.util.configureKotlinOptions

class KotlinLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(ProjectPlugins.KOTLIN)
                apply(ProjectPlugins.KAPT)
                apply(ProjectPlugins.KSP)
            }

            configureKotlinOptions()
        }
    }
}