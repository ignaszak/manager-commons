plugins {
    `maven-publish`
}

dependencies {
    implementation("com.google.code.gson:gson:2.7")
}

publishing {
    repositories {
        maven {
            url = uri("$buildDir/../../build/repository")
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.ignaszak"
            artifactId = "manager.commons.json"
            version = "1.0-SNAPSHOT"
            from(components["java"])
        }
    }
}

tasks.jar {
    into("META-INF/maven/net/ignaszak/manager/commons/json") {
        from("build/publications/maven/pom-default.xml")
        rename(".*", "pom.xml")
    }
}