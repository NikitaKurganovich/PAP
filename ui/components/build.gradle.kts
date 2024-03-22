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
    implementation(libs.androidx.compose.ui.toolingPreview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui.toolingPreview)
    implementation(project(":core:model"))
    implementation(libs.voyager.navigator)
    implementation(libs.tabler.icons)
    implementation(libs.ui.text.google.fonts)
    implementation(libs.androidx.core)

}