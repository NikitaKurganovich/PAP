plugins {
    id(ProjectPlugins.PAP_ANDROID_LIBRARY)
}

android {
    namespace = "dev.babananick.pap.core.navigation"
}

dependencies {
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.transitions)
    implementation(libs.voyager.tab.navigator)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.firebase.auth.ktx)

    implementation(project(":feature:home:lecturechoose"))
    implementation(project(":feature:personaltests:testchoose"))
    implementation(project(":feature:profile"))
    implementation(project(":ui:components"))
    implementation(project(":feature:login"))
    implementation(project(":feature:login"))
    implementation(project(":feature:registration"))
    implementation(project(":core:authorization"))
}