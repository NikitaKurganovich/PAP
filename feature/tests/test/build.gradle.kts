plugins {
    id(ProjectPlugins.PAP_ANDROID_FEATURE)

}

android {
    namespace = "dev.babananick.pap.feature.tests.test"
}

dependencies {

    implementation(project(":core:util"))
}