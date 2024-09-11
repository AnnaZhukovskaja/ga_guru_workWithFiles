plugins {
    id("java")
}

group = "by.zhukovskaya"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("com.codeborne:selenide:7.0.2")
    testImplementation("commons-io:commons-io:2.16.1")
    testImplementation("com.codeborne:pdf-test:1.9.0")
    testImplementation("com.codeborne:xls-test:1.4.3")
    testImplementation("com.opencsv:opencsv:5.9")
    testImplementation("com.fasterxml.jackson.core:jackson-core:2.13.1")
    testImplementation("com.fasterxml.jackson.core:jackson-databind:2.10.0")

}

tasks.test {
    useJUnitPlatform()
}