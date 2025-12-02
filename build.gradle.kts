plugins {
    id("java")
}

group = "com.jkn"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.mysql:mysql-connector-java:5.1.13")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}