plugins {
    id("custom.java-conventions")
    id("custom.test-conventions")
    id("custom.batch-conventions")
    id("nu.studer.jooq") version "6.0"
}

dependencies {
    implementation(project(":modules:core"))
    implementation(Spring.boot.batch)
    implementation(Spring.boot.jooq)
    implementation("mysql:mysql-connector-java:_")
}

jooq {
    version.set("3.19.10")
    edition.set(nu.studer.gradle.jooq.JooqEdition.OSS)

    configurations {
        create("main") {
            dependencies {

                // MySQL 드라이버 의존성 추가
                jooqGenerator("mysql:mysql-connector-java:_")
            }

            jooqConfiguration.apply {
                jdbc.apply {
                    driver = "com.mysql.cj.jdbc.Driver"
                    url = "jdbc:mysql://localhost:3307/ccs"
                    user = System.getenv("CCS_BATCH_DB_USER")
                    password = System.getenv("CCS_BATCH_DB_PASSWORD")
                }

                generator.apply {
                    database.apply {
                        name = "org.jooq.meta.mysql.MySQLDatabase"
                        inputSchema = "ccs"
                        includes = ".*"
                        excludes = "BATCH_.*"
                    }

                    target.apply {
                        packageName = "com.keehwan.batch.generated"
                        directory = "build/generated-src/jooq/main"
                    }
                }
            }
        }
    }
}