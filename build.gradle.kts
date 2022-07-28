plugins {
    id("java")
    id("application")
}

group = "com.lebedevrs9"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.8.1")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}

val javaMainClass = "com.lebedevrs9.testtask.Concordance"

application {
    mainClassName = javaMainClass
}