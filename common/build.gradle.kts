import config.Dep
import config.Versions

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.squareup.sqldelight")
}

val frameworkName = "common"
val ideaActive = System.getProperty("idea.active") == "true"

sqldelight {
    database("GreetingDatabase") {
        packageName = "com.github.aakira.multithreadmpp.db"
        sourceFolders = listOf("sqldelight")
    }
    linkSqlite = false
}

kotlin {
    android()

    val iosArm32 = iosArm32("iosArm32")
    val iosArm64 = iosArm64("iosArm64")
    val iosX64 = iosX64("iosX64")

    if (ideaActive) {
        iosX64("ios")
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(Dep.Kotlin.common)
                implementation(Dep.Coroutines.common)
                implementation(Dep.SQLDelightMT.runtime)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Dep.Kotlin.jvm)
                implementation(Dep.Coroutines.android)
                implementation(Dep.SQLDelightMT.android)
            }
        }

        val iosMain = if (ideaActive) getByName("iosMain") else create("iosMain")

        iosMain.dependencies {
            implementation(Dep.Coroutines.native)
            implementation(Dep.SQLDelightMT.ios)
        }

        val iosArm32Main by getting
        val iosArm64Main by getting
        val iosX64Main by getting

        configure(listOf(iosArm32Main, iosArm64Main, iosX64Main)) {
            dependsOn(iosMain)
        }
    }

    configure(listOf(iosArm32, iosArm64, iosX64)) {
        compilations {
            val main by getting {
                extraOpts("-Xobjc-generics")
            }
        }

        binaries.framework {
            baseName = frameworkName
            linkerOpts("-lsqlite3")
        }
    }

    tasks.register<org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask>("debugFramework") {
        baseName = frameworkName
        group = "Universal framework"
        description = "Builds a universal debug framework"

        from(iosX64.binaries.getFramework("DEBUG"))
    }

    tasks.register<org.jetbrains.kotlin.gradle.tasks.FatFrameworkTask>("releaseFramework") {
        baseName = frameworkName
        group = "Universal framework"
        description = "Builds a universal release framework"

        from(iosArm64.binaries.getFramework("RELEASE"), iosArm32.binaries.getFramework("RELEASE"))
    }
}

android {
    compileSdkVersion(Versions.compileSdkVersion)
    buildToolsVersion = Versions.buildToolsVersion
    defaultConfig {
        minSdkVersion(Versions.minSdkVersion)
        targetSdkVersion(Versions.targetSdkVersion)
        versionCode = Versions.mobileVersionCode
        versionName = Versions.mobileVersionName
    }

    sourceSets.forEach {
        it.manifest.srcFile("src/androidMain/AndroidManifest.xml")
    }
}
