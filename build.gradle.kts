import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    application
}

application {
    mainClassName = "com.s4n.dronemanager.app.DroneManager"
}

group = "com.s4n"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

val spekVersion = "2.0.11"
val assertjVersion = "3.16.1"
val mockkVersion = "1.10.0"
val coroutinesVersion = "1.3.7"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutinesVersion")

    testImplementation("io.mockk:mockk:$mockkVersion")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spekVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spekVersion")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    withType<Test> {
        useJUnitPlatform {
            includeEngines("spek2")
        }
    }
}
