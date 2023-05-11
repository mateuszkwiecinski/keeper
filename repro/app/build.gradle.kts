import com.slack.keeper.optInToKeeper

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("com.slack.keeper")
    id("java-test-fixtures")
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenCentral()
    google()
}

android {
    compileSdk = 33
    namespace = "com.keeper.repro"

    defaultConfig {
        minSdk = 26
        applicationId = "com.keeper.repro"
    }

    testBuildType = "release"

    buildTypes {
        named("release") {
            isMinifyEnabled = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

keeper {
    emitDebugInformation.set(true)
}

androidComponents {
    beforeVariants(selector().withName("release")) { it.optInToKeeper() }
}

dependencies {
    androidTestImplementation(testFixtures(project(":repro:ui-testing")))
}


