plugins {
    id("java-library")
    id("maven-publish")
}

group = "com.ersikthy"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.register<JavaExec>("runExample") {
    group = "application"
    description = "Runs the menu library demo example"
    mainClass.set("com.ersikthy.consolemenu.example.Example")
    classpath = sourceSets["main"].runtimeClasspath
    standardInput = System.`in`
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}


