plugins {
    id("java")
    id("application")
}

group = "com.jkn"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mariadb.jdbc:mariadb-java-client:3.3.2")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

application {
    mainClass.set("com.jkn.Main")
}
