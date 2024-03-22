plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
}

android {
    namespace = "dev.babananick.pap"
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.voyager.tab.navigator)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.tabler.icons)
}