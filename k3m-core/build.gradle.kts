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
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = libGroupId
            artifactId = libArtifactId
            version = libVersion

            from(components["java"])
        }
    }
}

dependencies {
//    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.22-1.0.17")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}