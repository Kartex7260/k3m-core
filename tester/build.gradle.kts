plugins {
    kotlin("jvm")
    id("com.google.devtools.ksp") version "1.9.22-1.0.17"
}

group = "kanti.k3m"

val libVersion: String by project
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    ksp(project(":k3m-test-impl"))
    implementation(project(":k3m-test-impl"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(8)
}