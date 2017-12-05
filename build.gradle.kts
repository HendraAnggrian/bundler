buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.0.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
        classpath("com.novoda:bintray-release:0.7.0")
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

/**
./gradlew :bundler-annotations:bintrayUpload -PbintrayUser=hendraanggrian -PdryRun=false -PbintrayKey=
./gradlew :bundler-compiler:bintrayUpload -PbintrayUser=hendraanggrian -PdryRun=false -PbintrayKey=
./gradlew :bundler:bintrayUpload -PbintrayUser=hendraanggrian -PdryRun=false -PbintrayKey=
 */