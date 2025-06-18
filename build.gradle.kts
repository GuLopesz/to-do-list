plugins {
    id("java")
}

group = "br.ifsp"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.xerial:sqlite-jdbc:3.45.2.0")
    implementation ("org.slf4j:slf4j-api:2.0.12")
    implementation ("ch.qos.logback:logback-classic:1.4.11")
    implementation("org.xerial:sqlite-jdbc:3.45.1.0")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}