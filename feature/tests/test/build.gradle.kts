plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
}

android {
    namespace = "dev.babananick.pap.feature.personaltests.test"
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
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(project(":core:common"))
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(project(":core:model"))

    implementation(libs.hilt.android)
    implementation(libs.hilt.core)
    implementation(libs.hilt.navigationCompose)
}