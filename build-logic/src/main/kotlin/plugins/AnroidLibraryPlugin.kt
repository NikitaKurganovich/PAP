package plugins

import ApplicationConfig
import ProjectPlugins
import com.android.build.gradle.LibraryExtension
import com.android.build.gradle.TestedExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import plugins.util.configureAndroid
import plugins.util.configureKotlinOptions

class AndroidLibraryPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(ProjectPlugins.ANDROID_LIBRARY)
                apply(ProjectPlugins.KOTLIN_ANDROID)
                apply(ProjectPlugins.KAPT)
                apply(ProjectPlugins.KSP)
            }

            configureKotlinOptions()
            extensions.configure<LibraryExtension> {
                defaultConfig.targetSdk = ApplicationConfig.TARGET_SDK_VERSION
                configureAndroid(this)
            }

            extensions.configure<TestedExtension> {
                (project.findProperty("testBuildType") as? String)?.let {
                    testBuildType = it
                }
            }
        }
    }
}