import Conf.Versions

plugins {
    `maven-publish`
}

dependencies {
    implementation("org.springframework:spring-web:${Versions.SPRING_WEB}") {
        exclude(group = "org.springframework", module = "spring-core")
        exclude(group = "org.springframework", module = "spring-beans")
    }
}

publishing {
    repositories {
        maven {
            url = uri("$buildDir/${Conf.LOCAL_PUBLISH_REPOSITORY}")
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "net.ignaszak"
            artifactId = "manager-commons-error"
            version = "master-SNAPSHOT"
            from(components["java"])
        }
    }
}

tasks.jar {
    into("META-INF/maven/net/ignaszak/manager/commons/error") {
        from("build/publications/maven/pom-default.xml")
        rename(".*", "pom.xml")
    }
}