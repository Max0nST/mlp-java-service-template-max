import java.net.URI

plugins {
    kotlin("jvm") version "1.9.21"
}

group = "com.mlp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        name = "nexus-public"
        url = URI("https://nexus-open.just-ai.com/repository/maven-public/")
    }
}

val mlpSdkVersion = "CAILA-2399_nexus-open-repo-SNAPSHOT"
val logbackVersion = "1.2.6"

dependencies {
    implementation("com.mlp:mlp-sdk:$mlpSdkVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.11.3")
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

tasks.register<Copy>("copyLibs") {
    destinationDir = layout.buildDirectory.get().asFile.resolve("dependencies")
    from(configurations["runtimeClasspath"])
}
