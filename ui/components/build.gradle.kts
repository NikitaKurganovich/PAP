plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.PAP_ANDROID_COMPOSE)
}

android {
    namespace = "dev.babananick.pap.ui.components"
}

dependencies {
    implementation(project(":core:model"))
    implementation(libs.voyager.navigator)
    implementation(libs.tabler.icons)

}