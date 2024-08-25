plugins {
    id("custom.java-conventions")
    id("custom.test-conventions")
    id("custom.spring-conventions")
}

dependencies {
    implementation(project(":modules:core"))
    implementation(Spring.boot.web)
    implementation(Spring.boot.data.jpa)
}
