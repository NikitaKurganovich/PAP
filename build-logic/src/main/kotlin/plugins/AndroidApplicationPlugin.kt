package plugins

import ApplicationConfig
import ApplicationConfig.KEYSTORE_PROPERTIES_FILE_NAME
import ProjectPlugins
import com.android.build.api.dsl.ApplicationExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import plugins.util.configureAndroid
import plugins.util.configureKotlinOptions
import java.util.Properties

class AndroidApplicationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(ProjectPlugins.ANDROID_APPLICATION)
                apply(ProjectPlugins.KOTLIN_ANDROID)
                apply(ProjectPlugins.KAPT)
                apply(ProjectPlugins.KSP)
                apply(ProjectPlugins.KOTLIN_PARCELIZE)
            }

            configureKotlinOptions()

            extensions.configure<ApplicationExtension> {
                configureAndroid(this)

                defaultConfig {
                    applicationId = ApplicationConfig.APPLICATION_ID
                    targetSdk = ApplicationConfig.TARGET_SDK_VERSION
                    versionCode = ApplicationConfig.VERSION_CODE
                    versionName = ApplicationConfig.VERSION_NAME
                }

                signingConfigs {
                    create(ApplicationConfig.RELEASE_BUILD_TYPE) {
                        if (rootProject.file(KEYSTORE_PROPERTIES_FILE_NAME).canRead()) {
                            val properties = getKeystoreProperties()
                            keyAlias = properties.getProperty("keyAlias")
                            keyPassword = properties.getProperty("keyPassword")
                            storeFile = file(properties.getProperty("storeFile"))
                            storePassword = properties.getProperty("storePassword")
                        } else {
                            println(
                                """
                       Cannot create a release signing config.
                       The file, $KEYSTORE_PROPERTIES_FILE_NAME, either does not exist or cannot be read from.
                                """.trimIndent(),
                            )
                        }
                    }
                }

                buildTypes {
                    getByName(ApplicationConfig.RELEASE_BUILD_TYPE) {
                        signingConfig = signingConfigs.getByName(ApplicationConfig.RELEASE_BUILD_TYPE)
                        isMinifyEnabled = true
                        isShrinkResources = true
                        proguardFiles(
                            getDefaultProguardFile("proguard-android-optimize.txt"),
                            "proguard-rules.pro",
                        )
                    }
                }
            }
        }
    }
}

private fun Project.getKeystoreProperties() = Properties().apply {
    load(rootProject.file(KEYSTORE_PROPERTIES_FILE_NAME).inputStream())
}