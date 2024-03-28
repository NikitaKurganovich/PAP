plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.PAP_ANDROID_COMPOSE)
    id(ProjectPlugins.PAP_ANDROID_HILT)
}

android {
    namespace = "dev.babananick.pap"
}

dependencies {
    implementation(libs.voyager.tab.navigator)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.tabler.icons)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(project(":core:common"))
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(project(":core:model"))
    implementation(project(":ui:components"))
}