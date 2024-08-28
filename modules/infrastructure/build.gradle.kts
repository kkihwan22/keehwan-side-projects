plugins {
    id("custom.java-conventions")
    id("custom.test-conventions")
    id("custom.spring-conventions")
}

dependencies {
    implementation(Spring.boot.web)
    implementation("commons-io:commons-io:_")
    implementation(platform("software.amazon.awssdk:bom:2.20.136"))
    implementation("software.amazon.awssdk:aws-core")
    implementation("software.amazon.awssdk:sdk-core")
    implementation("software.amazon.awssdk:sts")
    implementation("software.amazon.awssdk:s3")

    testImplementation("org.testcontainers:testcontainers:_")
    testImplementation("org.testcontainers:localstack:_")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.1")
    }
}
