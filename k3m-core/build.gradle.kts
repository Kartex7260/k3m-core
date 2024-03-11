plugins {
    kotlin("jvm") version "1.9.22"
}

group = "kanti.k3m"

val libVersion: String by project
version = libVersion

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.22-1.0.17")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}