plugins {
	java
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	// Liquibase
	implementation("org.liquibase:liquibase-core")
	// Swagger
	implementation ("io.swagger.core.v3:swagger-annotations:2.2.2")
	implementation ("org.springdoc:springdoc-openapi-ui:1.6.11")
	implementation ("io.springfox:springfox-swagger2:2.9.2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	// Jackon
	implementation ("com.fasterxml.jackson.core:jackson-databind:2.13.0")
	// PostgreSQL
	runtimeOnly ("org.postgresql:postgresql")
	runtimeOnly ("com.h2database:h2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.bootBuildImage {
	builder.set("paketobuildpacks/builder-jammy-base:latest")
}
