plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.gradleversions.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "pap.android.application"
            implementationClass = "plugins.AndroidApplicationPlugin"
        }
        register("androidLibrary") {
            id = "pap.android.library"
            implementationClass = "plugins.AndroidLibraryPlugin"
        }
        register("androidFeature") {
            id = "pap.android.feature"
            implementationClass = "plugins.AndroidFeaturePlugin"
        }
        register("androidCompose") {
            id = "pap.android.compose"
            implementationClass = "plugins.AndroidComposePlugin"
        }
        register("androidHilt") {
            id = "pap.android.hilt"
            implementationClass = "plugins.AndroidHiltPlugin"
        }
        register("kotlinLibrary") {
            id = "pap.kotlin.library"
            implementationClass = "plugins.KotlinLibraryPlugin"
        }
        register("gradleVersions") {
            id = "pap.gradleversions"
            implementationClass = "plugins.GradleVersionsPlugin"
        }
    }
}