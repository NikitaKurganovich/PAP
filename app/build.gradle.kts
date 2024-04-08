@file:Suppress("PropertyName", "VariableNaming")

import plugins.util.coil
import plugins.util.getLibsVersionCatalog


plugins {
    id(ProjectPlugins.PAP_ANDROID_APPLICATION)
    id(ProjectPlugins.PAP_ANDROID_COMPOSE)
    id(ProjectPlugins.PAP_ANDROID_HILT)
}

android {
    namespace = "dev.babananick.pap"
}

kapt {
    correctErrorTypes = true
}

dependencies {
    val versionCatalog = getLibsVersionCatalog()
    coil(versionCatalog)

    implementation(project(":core:domain"))
    implementation(project(":core:model"))
    implementation(project(":ui:components"))
    implementation(project(":feature:navigation"))
    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.firebase.auth.ktx)
    implementation(libs.firebase.database)
    implementation(libs.firebase.common)
    implementation(libs.firebase.database.ktx)

    implementation(libs.androidx.appcompat)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.testManifest)

}