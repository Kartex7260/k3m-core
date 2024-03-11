plugins {
    kotlin("jvm")
}

group = "kanti.k3m"

val libVersion: String by project
version = libVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":k3m-core"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}