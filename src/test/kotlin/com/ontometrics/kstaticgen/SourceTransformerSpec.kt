package com.ontometrics.kstaticgen

import mu.KotlinLogging
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import java.nio.file.Paths
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

/**
 *
 *
 * User: Rob
 * Date: 5/22/17
 * Time: 8:13 PM
 *
 * (c) ontometrics 2017, All Rights Reserved
 */
object SourceTransformerSpec : Spek({

    val log = KotlinLogging.logger {}

    val markdownFile = Paths.get("src/test/resources/markdown/kotlin-on-android.md").toFile()

    describe("given some markdown files in a dir"){
        on("walking the directory"){
            it("will output a file for each template that was found"){
                val dir = Paths.get("src/test/resources/markdown").toFile()
                var generated = 0
                dir.walkTopDown().iterator().forEach { file ->
                    log.debug { "item: $file" }
                    assertNotNull(file)
                    if (file.isFile) {
                        generated++
                    }
                }

                assertEquals(generated, 1)

            }
        }
        assertTrue(markdownFile.exists())
        on("starting a process"){
            val src = markdownFile.readText()
            val flavour = CommonMarkFlavourDescriptor()
            val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(src)
            val html = HtmlGenerator(src, parsedTree, flavour).generateHtml()
            it("should make html files for each"){
                assertEquals(html, "<body><h3>Android Finally Has a Modern Language</h3><p>Still prefer <strong>Swift</strong> but Kotlin is awesome.</p></body>")
            }
        }
    }

    describe("write out the generated markdown"){
        on("walking the directory"){
            it("should make an HTML file for each"){
                val dir = Paths.get("src/test/resources/markdown").toFile()
                //val outputDir = Paths.get("src/test/resources/site")
                var generated = 0
                dir.walkTopDown().iterator().forEach { file ->
                    log.debug { "item: $file" }
                    assertNotNull(file)
                    if (file.isFile) {
                        generated++
                        val src = markdownFile.readText()
                        val flavour = CommonMarkFlavourDescriptor()
                        val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(src)
                        val html = HtmlGenerator(src, parsedTree, flavour).generateHtml()
                        val outputFilename = ("src/test/resources/site" + "/" + file.name).replace(".md", ".html")
                        val outputFile = Paths.get(outputFilename).toFile()
                        outputFile.writeText(html)

                        log.debug { "wrote file to: $outputFile" }
                    }
                }

            }
        }
    }
})