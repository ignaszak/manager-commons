import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import Conf.Versions

plugins {
    kotlin("jvm") version "1.3.61"
}

java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

allprojects {
    group = "net.ignaszak"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }

    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "java")
    apply(plugin = "groovy")

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation(kotlin("stdlib"))

        testImplementation("org.spockframework:spock-core:${Versions.SPOCK_CORE}")
        testImplementation("org.codehaus.groovy:groovy-all:${Versions.GROOVY_ALL}")
        testImplementation("cglib:cglib-nodep:${Versions.CGLIB_NODEP}")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }
}

tasks.register("publishAll") {
    group = "publishing"
    description = "Publish all modules"

    subprojects.forEach {s ->
        if (s.tasks.findByName("publish") != null) {
            dependsOn(s.tasks["publish"])
        }
    }
}
