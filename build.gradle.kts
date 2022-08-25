plugins {
    kotlin("jvm") version "1.7.10"
    `maven-publish`
}

group = "com.displee"
version = "7.0.0"
description = "A library written in Kotlin used to read and write to all cache formats of RuneScape."

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.github.jponge:lzma-java:1.3")
    implementation("org.apache.ant:ant:1.10.12")
    implementation("com.displee:disio:2.2")
    testImplementation("junit:junit:4.13.2")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

tasks {
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }
}

publishing {
    repositories {
        maven {
            name = "Github"
            url = uri("https://maven.pkg.github.com/Qodat/rs-cache-library")
            credentials {
                username = System.getenv("GITHUB_USER")
                password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
    publications {
        register("jar", MavenPublication::class.java) {
            from(components["java"])
            pom {
                url.set("https://github.com/Qodat/rs-cache-library.git")
            }
        }
    }
}
