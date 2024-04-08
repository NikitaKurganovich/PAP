package plugins.util

import ApplicationConfig
import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies


internal fun Project.configureAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = ApplicationConfig.COMPILE_SDK_VERSION

        defaultConfig {
            minSdk = ApplicationConfig.MIN_SDK_VERSION
            testInstrumentationRunner = ApplicationConfig.TEST_INSTRUMENTATION_RUNNER
        }

        buildFeatures {
            buildConfig = true
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }

        buildTypes {
            getByName(ApplicationConfig.DEBUG_BUILD_TYPE) {
                enableUnitTestCoverage = true
                enableAndroidTestCoverage = true
            }
            getByName(ApplicationConfig.RELEASE_BUILD_TYPE) {
                enableUnitTestCoverage = true
                enableAndroidTestCoverage = true
            }
        }
    }
    dependencies {
        val libs = getLibsVersionCatalog()
        androidTestImplementation(libs.findLibrary("androidx.test.runner").get())
    }
}
