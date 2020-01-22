plugins {
    `maven-publish`
}

dependencies {}

publishing {
    repositories {
        maven {
            url = uri("$buildDir/${Conf.LOCAL_PUBLISH_REPOSITORY}")
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.ignaszak"
            artifactId = "manager-commons-model"
            version = "master-SNAPSHOT"
            from(components["java"])
        }
    }
}

tasks.jar {
    into("META-INF/maven/net/ignaszak/manager/commons/model") {
        from("build/publications/maven/pom-default.xml")
        rename(".*", "pom.xml")
    }
}