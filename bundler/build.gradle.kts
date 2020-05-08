plugins {
    android("library")
    kotlin("android")
    `bintray-release`
}

android {
    compileSdkVersion(SDK_TARGET)
    defaultConfig {
        minSdkVersion(SDK_MIN)
        targetSdkVersion(SDK_TARGET)
        versionName = RELEASE_VERSION
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("proguard-rules.pro")
        javaCompileOptions {
            annotationProcessorOptions {
                includeCompileClasspath = true
            }
        }
    }
    sourceSets {
        getByName("main") {
            manifest.srcFile("AndroidManifest.xml")
            java.srcDir("src")
            res.srcDir("res")
        }
    }
    libraryVariants.all {
        generateBuildConfigProvider?.configure {
            enabled = false
        }
    }
}

dependencies {
    api(project(":$RELEASE_ARTIFACT-annotations"))
    api(androidx("fragment"))

    testImplementation(kotlin("test-junit", VERSION_KOTLIN))
    androidTestImplementation(kotlin("stdlib", VERSION_KOTLIN))
    androidTestImplementation(kotlin("test", VERSION_KOTLIN))
    androidTestImplementation(androidx("test", "core-ktx", VERSION_ANDROIDX_TEST))
    androidTestImplementation(androidx("test", "runner", VERSION_ANDROIDX_TEST))
    androidTestImplementation(androidx("test", "rules", VERSION_ANDROIDX_TEST))
    androidTestImplementation(androidx("test.ext", "junit-ktx", VERSION_ANDROIDX_JUNIT))
    androidTestImplementation(androidx("test.ext", "truth", VERSION_ANDROIDX_TRUTH))
    androidTestImplementation(androidx("test.espresso", "espresso-core", VERSION_ESPRESSO))
}

publish {
    bintrayUser = BINTRAY_USER
    bintrayKey = BINTRAY_KEY
    dryRun = false
    repoName = RELEASE_REPO

    userOrg = RELEASE_USER
    groupId = RELEASE_GROUP
    artifactId = RELEASE_ARTIFACT
    publishVersion = RELEASE_VERSION
    desc = RELEASE_DESC
    website = RELEASE_WEB
}
