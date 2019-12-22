package config

object Versions {
    const val mobileVersionCode = 1
    const val mobileVersionName = "1.0.0"

    const val compileSdkVersion = 28
    const val minSdkVersion = 21
    const val targetSdkVersion = 28
    const val buildToolsVersion = "28.0.3"
}

object Dep {

    const val kotlin_version = "1.3.61"
    private const val sql_delight_version = "1.2.1"
    private const val sql_delight_mt_version = "1.3.0-mt2-SNAPSHOT"

    object Kotlin {
        const val jvm = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        const val common = "org.jetbrains.kotlin:kotlin-stdlib-common:$kotlin_version"
        const val js = "org.jetbrains.kotlin:kotlin-stdlib-js:$kotlin_version"
    }

    object BuildPlugins {
        const val androidTools = "com.android.tools.build:gradle:3.5.2"
        const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:$kotlin_version"
        const val sqlDelight = "com.squareup.sqldelight:gradle-plugin:$sql_delight_version"
        const val sqlDelightMT = "co.touchlab.sqldelight:gradle-plugin:$sql_delight_mt_version"
    }

    object Serialization {
        private const val version = "0.14.0"
        const val runtime = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$version"
        const val common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$version"
        const val native = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$version"
        const val js = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$version"
    }

    object Coroutines {
        private const val version = "1.3.3-native-mt"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$version"
        const val native = "org.jetbrains.kotlinx:kotlinx-coroutines-core-native:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val js = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$version"
    }

    object Ktor {
        private const val client_version = "1.2.6"
        const val clientCore = "io.ktor:ktor-client-core:$client_version"
        const val clientJson = "io.ktor:ktor-client-gson:$client_version"
        const val clientAndroid = "io.ktor:ktor-client-android:$client_version"
        const val clientJvm = "io.ktor:ktor-client-core-jvm:$client_version"
        const val clientJsonJvm = "io.ktor:ktor-client-json-jvm:$client_version"
        const val clientIos = "io.ktor:ktor-client-ios:$client_version"
        const val clientJsonIos = "io.ktor:ktor-client-json-native:$client_version"
        const val clientJs = "io.ktor:ktor-client-js:$client_version"
        const val clientJsonJs = "io.ktor:ktor-client-json-js:$client_version"

        object Serialization {
            const val core = "io.ktor:ktor-client-serialization:$client_version"
            const val native = "io.ktor:ktor-client-serialization-native:$client_version"
            const val jvm = "io.ktor:ktor-client-serialization-jvm:$client_version"
        }
    }

    object SQLDelight {
        const val android = "com.squareup.sqldelight:android-driver:$sql_delight_version"
        const val native = "com.squareup.sqldelight:native-driver:$sql_delight_version"
        const val jvm = "com.squareup.sqldelight:sqlite-driver:$sql_delight_version"
    }

    object SQLDelightMT {
        const val runtime = "co.touchlab.sqldelight:runtime:$sql_delight_mt_version"
        const val android = "co.touchlab.sqldelight:android-driver:$sql_delight_mt_version"
        const val ios = "co.touchlab.sqldelight:ios-driver:$sql_delight_mt_version"
        const val jvm = "co.touchlab.sqldelight:sqlite-driver:$sql_delight_mt_version"
        const val coroutinesExt =
            "co.touchlab.sqldelight:coroutines-extensions:$sql_delight_mt_version"
    }

    object AndroidX {
        private const val appcompatVersion = "1.1.0"
        private const val coreVersion = "1.2.0-beta01"
        private const val lifecycleVersion = "2.2.0-rc01"
        private const val constraintLayoutVersion = "2.0.0-beta2"

        const val appCompat = "androidx.appcompat:appcompat:$appcompatVersion"
        const val coreKtx = "androidx.core:core-ktx:$coreVersion"

        const val service = "androidx.lifecycle:lifecycle-service:$lifecycleVersion"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycleVersion"

        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    }
}
