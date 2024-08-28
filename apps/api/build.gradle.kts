plugins {
    id("custom.java-conventions")
    id("custom.test-conventions")
    id("custom.spring-conventions")
}

dependencies {
    implementation(project(":modules:core"))
    implementation(project(":modules:persistence"))
    implementation(project(":modules:infrastructure"))

    implementation(Spring.boot.security)
    implementation(Spring.boot.oauth2Client)
    implementation(Spring.boot.actuator)
    implementation(Spring.boot.web.toString()) {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation(Spring.boot.data.jpa)
    implementation(Spring.boot.mustache)

    implementation("mysql:mysql-connector-java:_")
}


