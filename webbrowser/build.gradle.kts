import com.vanniktech.maven.publish.SonatypeHost

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("com.vanniktech.maven.publish") version "0.28.0"
}

kotlin {
    targetHierarchy.default()
    androidTarget {
        publishLibraryVariants("release")
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
        publishLibraryVariants("release", "debug")
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "webbrowser"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
            }
        }
        val androidMain by getting {
            dependencies {
                implementation("androidx.startup:startup-runtime:1.1.1")
                implementation("androidx.browser:browser:1.8.0")
            }
        }
        val iosMain by getting {
            dependencies {
            }
        }
    }
}

android {
    namespace = "com.stevdza_san"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    coordinates(
        groupId = "com.stevdza-san",
        artifactId = "browser-kmp",
        version = "1.0.3"
    )

    // Configure POM metadata for the published artifact
    pom {
        name.set("Web Browser KMP")
        description.set("Library used to open up a web browser on both Android/iOS.")
        inceptionYear.set("2024")
        url.set("https://github.com/stevdza-san/WebBrowserKMP")

        licenses {
            license {
                name.set("MIT")
                url.set("https://opensource.org/licenses/MIT")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("stevdza-san")
                name.set("Stefan Jovanovic")
                email.set("stefan.jovanavich@gmail.com")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/stevdza-san/WebBrowserKMP")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}

task("testClasses") {}