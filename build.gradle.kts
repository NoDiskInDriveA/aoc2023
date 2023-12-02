plugins {
    kotlin("jvm") version "1.9.21"
}

sourceSets {
    main {
        kotlin.srcDir("src")
    }
    test {
        kotlin.srcDir("test")
        resources.srcDir("resources")
    }
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks {
    wrapper {
        gradleVersion = "8.5"
    }
    test {
        useJUnitPlatform()
    }
}

kotlin {
    jvmToolchain(17)
}