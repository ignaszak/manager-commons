import Conf.Versions

plugins {
    `maven-publish`
}

dependencies {
    compile(project(":model"))

    implementation("com.google.code.gson:gson:${Versions.GSON}")
    implementation("io.jsonwebtoken:jjwt-api:${Versions.JJWT}")
    implementation("io.jsonwebtoken:jjwt-impl:${Versions.JJWT}")
    implementation("io.jsonwebtoken:jjwt-jackson:${Versions.JJWT}")
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
            artifactId = "manager-commons-json"
            version = "master-SNAPSHOT"
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