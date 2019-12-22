buildscript {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
    dependencies {
        classpath(config.Dep.BuildPlugins.androidTools)
        classpath(config.Dep.BuildPlugins.kotlinGradlePlugin)
        classpath(config.Dep.BuildPlugins.sqlDelightMT)
    }
}

allprojects {
    repositories {
        mavenLocal()
        google()
        jcenter()
        maven(url = "https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
