plugins {
	kotlin("jvm") version "1.9.22" apply false
	id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "k3m"
include("k3m-core")

buildscript {
	dependencies {
		classpath(kotlin("gradle-plugin", version = "1.9.22"))
	}
}
