plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
    id(ProjectPlugins.PAP_ANDROID_HILT)
    id(ProjectPlugins.PAP_ANDROID_COMPOSE)
}

android {
    namespace = "dev.babananick.pap.core.mainscreenspace"
}

dependencies {
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.tab.navigator)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(project(":feature:academic:lecturechoose"))
    implementation(project(":feature:tests:testchoose"))
    implementation(project(":feature:profile"))
    implementation(project(":ui:components"))
}