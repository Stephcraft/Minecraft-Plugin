
// apply Kotlin gradle plugin
plugins {
    kotlin("jvm") version "1.8.0"
    id("idea")
}

// dependency repositories
repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/groups/public/")
}

// dependency jars
dependencies {

    // kotlin standard library
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // spigot
    compileOnly("org.spigotmc:spigot-api:1.20.1-R0.1-SNAPSHOT")
}

// dependencies javadoc & sources
idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

// compile to Java 17
kotlin {
    jvmToolchain(17)
}

// metadata
group = "org.uocsclub"
version = "1.0-SNAPSHOT"

// auto export plugin jar to plugins/ server folder
tasks {
    jar {
        doLast {
            val exportPath = project.findProperty("plugin.export") as? String
            val exportName = project.findProperty("plugin.name") as? String

            when(exportPath) {
                null -> println("You can export the plugin jar to your server plugins folder by providing its path with the 'plugin.export' property of 'gradle.properties'")
                else -> {
                    copy {
                        from(archiveFile)
                        into(File(exportPath))
                        if(exportName != null) {
                            rename(".*", "$exportName.jar")
                        }
                    }
                }
            }
        }
    }
}