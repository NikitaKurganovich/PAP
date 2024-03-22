plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
}

android {
    namespace = "dev.babananick.pap"
}

dependencies {
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.tab.navigator)
}