plugins {
    kotlin("multiplatform") version "1.6.21"
}

group = "mwy3055"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    commonTestImplementation("org.junit.jupiter:junit-jupiter:5.8.2")
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
