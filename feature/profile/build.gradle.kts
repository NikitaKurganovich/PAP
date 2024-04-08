plugins {
    id(ProjectPlugins.PAP_ANDROID_FEATURE)

}

android {
    namespace = "dev.babananick.pap.feature.profile"
}

dependencies {
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.common.ktx)
    implementation(libs.firebase.auth.ktx)
}