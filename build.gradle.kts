import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.72"
    application
}

application {
    mainClassName = "com.s4n.app.DroneManager"
}

group = "com.s4n"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}

val spek_version = "2.0.11"
val assertj_version = "3.16.1"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    testImplementation("org.spekframework.spek2:spek-dsl-jvm:$spek_version")
    testImplementation("org.assertj:assertj-core:$assertj_version")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:$spek_version")
}

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}
