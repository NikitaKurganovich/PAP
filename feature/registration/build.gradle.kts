plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
}

android {
    namespace = "dev.babananick.pap"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)

    implementation(project(":ui:components"))

    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.tab.navigator)
    implementation(libs.voyager.hilt)


    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(project(":feature:login"))

    kapt(libs.hilt.android.compiler)
    implementation(libs.hilt.android)
    implementation(libs.hilt.core)
    implementation(libs.hilt.navigationCompose)

}