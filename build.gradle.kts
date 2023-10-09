import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20"
}

group = "digital.ald"
version = "1.0-SNAPSHOT"

val temporalVersion = "1.21.2"

repositories {
    mavenCentral()
}

dependencies {
    // temporal
    implementation("io.temporal", "temporal-sdk", temporalVersion)
    implementation("io.temporal", "temporal-kotlin", temporalVersion)

    implementation("ch.qos.logback:logback-classic:1.2.11")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}