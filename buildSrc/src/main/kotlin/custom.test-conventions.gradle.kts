plugins {
    java
    idea
}

repositories {
    mavenCentral()
}

tasks.test {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }

    maxHeapSize = "2g"
}

dependencies {
    // junit
    testImplementation(Testing.junit.jupiter)
    testRuntimeOnly(Testing.junit.jupiter.engine)

    // mockito
    testImplementation(Testing.mockito.core)
    testImplementation(Testing.mockito.junitJupiter)
    testImplementation(Testing.assertj.core)

    project.afterEvaluate {
        if (project.pluginManager.hasPlugin("custom.spring-conventions")) {
            testImplementation(Spring.boot.test)
        }
    }

    project.afterEvaluate {
        if (project.pluginManager.hasPlugin("custom.batch-conventions")) {
            testImplementation(Spring.boot.test)
            testImplementation(Spring.batchTest)
        }
    }
}