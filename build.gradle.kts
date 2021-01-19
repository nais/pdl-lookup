val ktorVersion = "1.5.0"
val logbackVersion = "1.2.3"
val logstashEncoderVersion = "6.5"
val junitJupiterVersion = "5.7.0"

val mainClassName = "io.nais.MainKt"

plugins {
   kotlin("jvm") version "1.4.21"
   kotlin("plugin.serialization") version "1.4.21"
}

java {
   sourceCompatibility = JavaVersion.VERSION_15
   targetCompatibility = JavaVersion.VERSION_15
}

repositories {
   mavenCentral()
   jcenter()
}

dependencies {
   implementation(kotlin("stdlib"))
   implementation("io.ktor:ktor-server-core:$ktorVersion")
   implementation("io.ktor:ktor-server-netty:$ktorVersion")
   implementation("ch.qos.logback:logback-classic:$logbackVersion")
   implementation("net.logstash.logback:logstash-logback-encoder:$logstashEncoderVersion")

   testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
   testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

tasks {
   withType<Jar> {
      archiveBaseName.set("app")

      manifest {
         attributes["Main-Class"] = "io.nais.MainKt"
         attributes["Class-Path"] = configurations.runtimeClasspath.get().joinToString(separator = " ") {
            it.name
         }
      }

      doLast {
         configurations.runtimeClasspath.get().forEach {
            val file = File("$buildDir/libs/${it.name}")
            if (!file.exists())
               it.copyTo(file)
         }
      }
   }

   withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
      kotlinOptions {
         jvmTarget = "15"
      }
   }

   withType<Test> {
      useJUnitPlatform()
   }

   withType<Wrapper> {
      gradleVersion = "6.8"
   }

}
