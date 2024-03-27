plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
}

android {
    namespace = "dev.babananick.pap.data.repository"
}

dependencies {
    implementation(project(":core:data:firebase"))
    implementation(libs.androidx.core)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database)
    implementation(libs.firebase.common)
    implementation(libs.firebase.database.ktx)
    implementation(libs.hilt.core)
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(project(":core:model"))

}