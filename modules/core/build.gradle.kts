plugins {
    id("custom.java-conventions")
    id("custom.test-conventions")
    id("custom.spring-conventions")
    id("custom.testfixture-conventions")
}

dependencies {
    implementation(Spring.boot.web)
    implementation(Spring.boot.data.jpa)
}
