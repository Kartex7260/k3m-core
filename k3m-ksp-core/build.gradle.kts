plugins {
    kotlin("jvm") version "1.9.22"
    id("maven-publish")
}

val libGroupId: String by project
val libArtifactId: String by project
val libVersion: String by project

group = libGroupId
version = libVersion

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.22-1.0.17")
    implementation("$libGroupId:$libArtifactId:$libVersion")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}