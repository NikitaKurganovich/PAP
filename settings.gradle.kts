@file:Suppress("UnstableApiUsage")

pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Psychology at Pocket"
include(":app")

include(":core:data:firebase")
include(":core:data:repository")
include(":core:domain")
include(":core:model")
include(":core:navigation")

include(":feature:home:lecture")
include(":feature:home:lecturechoose")
include(":feature:home:lecturetest")
include(":feature:home:lecturetestresult")

include(":feature:login")
include(":feature:registration")

include(":feature:personaltests:test")
include(":feature:personaltests:testchoose")
include(":feature:personaltests:testresult")

include(":feature:profile")

include(":ui:components")
include("core:authorization")
findProject(":core:authorization")?.name = "authorization"
