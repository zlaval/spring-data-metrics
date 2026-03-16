plugins {
    java
    id("org.springframework.boot") version "4.0.2"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.zlrx"
version = "0.0.1-SNAPSHOT"
description = "db-perf-test"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(25)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["datasourceMicrometerVersion"] = "2.1.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aspectj")
    implementation("io.micrometer:micrometer-registry-prometheus")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("net.ttddyy.observation:datasource-micrometer-spring-boot")
    compileOnly("org.projectlombok:lombok")
//    developmentOnly("org.springframework.boot:spring-boot-docker-compose")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
//    testImplementation("org.springframework.boot:spring-boot-starter-data-jdbc-test")
//    testImplementation("org.springframework.boot:spring-boot-starter-opentelemetry-test")
//    testImplementation("org.springframework.boot:spring-boot-starter-webmvc-test")
//    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("net.ttddyy.observation:datasource-micrometer-bom:${property("datasourceMicrometerVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
