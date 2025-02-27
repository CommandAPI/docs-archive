import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
	kotlin("jvm") version "2.1.0"
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://libraries.minecraft.net")
    }

    maven {
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }

	maven {
		url = uri("https://s01.oss.sonatype.org/content/repositories/snapshots")
	}
}

dependencies {
    api(libs.dev.jorel.commandapi.velocity.core)
    api(libs.org.jetbrains.kotlin.kotlin.stdlib)
    compileOnly(libs.com.mojang.brigadier)
    compileOnly(libs.com.mojang.authlib)
    compileOnly(libs.com.velocitypowered.velocity.api)
}

group = "dev.jorel"
version = "9.6.2-SNAPSHOT"
description = "commandapi-documentation-velocity-code"
java.sourceCompatibility = JavaVersion.VERSION_21
java.targetCompatibility = JavaVersion.VERSION_21

java {
    withSourcesJar()
    withJavadocJar()
}

tasks.withType<JavaCompile>() {
    options.encoding = "UTF-8"
}

tasks.withType<KotlinCompile> {
	compilerOptions.jvmTarget = JvmTarget.JVM_21
}

tasks.withType<Javadoc>() {
    options.encoding = "UTF-8"
}
