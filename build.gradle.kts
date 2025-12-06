plugins {
    kotlin("jvm") version "2.3.0-RC2"
}

sourceSets {
    main {
        kotlin.srcDir("kotlin/main")
        resources.srcDir("kotlin/resources")
    }
    test {
        kotlin.srcDirs("kotlin/test")
        resources.srcDir("kotlin/resources")
    }

}

tasks {
    wrapper {
        gradleVersion = "9.2.1"
    }
}

kotlin {
    compilerOptions {
        extraWarnings.set(true)
    }
}

tasks {
    register<KotlinDayGenerator>("createDay") {
        group = "generator"
        day = 1
        outputDir = layout.projectDirectory.dir("kotlin")
        force = false
    }
}

abstract class KotlinDayGenerator : DefaultTask() {
    @get:Input
    @get:Option(option = "day", description = "create day x")
    abstract val day: Property<Int>

    @get:Input
    @get:Option(option = "force", description = "force - overrides the current file(s)")
    abstract val force: Property<Boolean>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun createFile() {
        val txtDay = String.format("%02d", day.get())
        val mainContent = """

            fun main() {
                val testInput = readInput("Day${txtDay}_test")
                check(part1(testInput) == 1)
                val input = readInput("Day$txtDay")
                println(part1(input))
                
                check(part2(testInput) == 1)
                println(part2(input))
            }
            
            private fun part1(input: List<String>): Int {
                return input.size
            }
        
            private fun part2(input: List<String>): Int {
                return input.size
            }

        """.trimIndent()

        val targetMainFile = outputDir.file("main/Day$txtDay.kt").get().asFile
        if (!targetMainFile.exists() || force.get()) {
            targetMainFile.writeText(mainContent)
        }
    }
}
