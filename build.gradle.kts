plugins {
    kotlin("jvm") version "1.9.22"
    id("maven-publish")
}

group = "kanti.k3m"
version = "1.0-SNAPSHOT"

publishing {
    publications {
        val libGroupId: String by project
        val libArtifactId: String by project
        val libKspArtifactId: String by project
        val libVersion: String by project

        create<MavenPublication>(libKspArtifactId) {
            groupId = libGroupId
            artifactId = libKspArtifactId
            version = libVersion

            artifact(file("/out/artifacts/k3m_ksp_core/k3m-ksp-core.jar"))
        }

        create<MavenPublication>(libArtifactId) {
            groupId = libGroupId
            artifactId = libArtifactId
            version = libVersion

            artifact(file("/out/artifacts/k3m_core/k3m-core.jar"))
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}