package com.ontometrics.kstaticgen

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.KotlinModule
import mu.KotlinLogging
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 *
 *
 * User: Rob
 * Date: 5/23/17
 * Time: 11:22 PM
 *
 * (c) ontometrics 2017, All Rights Reserved
 */
object YAMLParserSpec : Spek({

    val log = KotlinLogging.logger {}

    describe("basic parsing of existing content"){
        on("some simple front matter YAML"){
            val universeConfigYamlFilePath = Paths.get("src/test/resources/YAML/universe-config.yaml")
            val loaded = loadFromFile(universeConfigYamlFilePath)
            it("should be possible to parse"){
                log.debug { "YAML: $loaded" }
                assertNotNull(loaded)

                assertTrue(loaded is ConfigDto)

                assertEquals(loaded.roundTime, 5)

            }
        }

    }
})

data class UniverseSizeDto(val maxGalaxies: Int, val maxSystems: Int, val maxPlanets: Int)

data class ResourcesDto(val crystal: Int, val gas: Int, val energy: Int)

data class StarterPlanetDto(val resources: ResourcesDto)

data class ConfigDto(val universeSize: UniverseSizeDto, val starterPlanet: StarterPlanetDto, val roundTime: Int)

fun loadFromFile(path: Path): ConfigDto {
    val mapper = ObjectMapper(YAMLFactory()) // Enable YAML parsing
    mapper.registerModule(KotlinModule()) // Enable Kotlin support

    return Files.newBufferedReader(path).use {
        mapper.readValue(it, ConfigDto::class.java)
    }
}