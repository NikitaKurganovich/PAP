plugins {
    id(ProjectPlugins.PAP_ANDROID_FEATURE)
}

android {
    namespace = "dev.babananick.pap.feature.coordinators.mainscreenspace"
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
}