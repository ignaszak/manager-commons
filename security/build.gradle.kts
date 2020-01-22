import Conf.Versions

plugins {
    `maven-publish`
}

dependencies {
    compile(project(":model"))
    compile(project(":error"))

    implementation("org.springframework.security:spring-security-core:${Versions.SPRING_SECURITY_CORE}") {
        exclude(group = "org.springframework", module = "spring-aop")
        exclude(group = "org.springframework", module = "spring-context")
        exclude(group = "org.springframework", module = "spring-beans")
        exclude(group = "org.springframework", module = "spring-expression")
        exclude(group = "org.springframework", module = "spring-core")
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
            artifactId = "manager-commons-security"
            version = "master-SNAPSHOT"
            from(components["java"])
        }
    }
}

tasks.jar {
    into("META-INF/maven/net/ignaszak/manager/commons/security") {
        from("build/publications/maven/pom-default.xml")
        rename(".*", "pom.xml")
    }
}