plugins {
    kotlin("multiplatform") version "1.7.22"
    `maven-publish`
    jacoco
    java
}

group = "mwy3055"
version = "1.0-beta07"

repositories {
    mavenCentral()
}

dependencies {
    commonMainImplementation("com.google.code.gson:gson:2.10")
    commonMainImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")

    commonTestImplementation(platform("org.junit:junit-bom:5.9.0"))
    commonTestImplementation("org.junit.jupiter:junit-jupiter")
    commonTestImplementation("org.assertj:assertj-core:3.23.1")
}

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }
    sourceSets {
        val jvmMain by getting
        val jvmTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
    }
}

jacoco {
    toolVersion = "0.8.7"
    reportsDirectory.set(file("$buildDir/jacocoTestReport"))
}

tasks.test {
    useJUnitPlatform()
    configure<JacocoTaskExtension> {
        excludes = listOf("*")
        includes = listOf(
            "$buildDir/jacoco/jvmTest.exec",
        )
        isIncludeNoLocationClasses = true
        setDestinationFile(File("$buildDir/jacoco/jvmTest.exec"))
    }
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks["allTests"])
    reports {
        html.required.set(true)
    }

    val fileFilter = listOf("**/R.class", "**/R$*.class", "**/BuildConfig.*", "**/Manifest*.*", "**/*Test*.*")
    val debugTree = fileTree(baseDir = "$buildDir/classes") {
        excludes.addAll(fileFilter)
    }

    val mainSrc = "$projectDir/src/jvmMain/kotlin"
    sourceDirectories.from(mainSrc)
    classDirectories.from(debugTree)
    executionData.from("build/jacoco/jvmTest.exec")
}

configurations.all {
    resolutionStrategy {
        eachDependency {
            if (requested.group == "org.jacoco") {
                useVersion("0.8.7")
            }
        }
    }
}