package com.ontometrics.kstaticgen

//import net.dgardiner.markdown.MarkdownProcessor
import mu.KotlinLogging
import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

/**
 *
 *
 * User: Rob
 * Date: 4/27/17
 * Time: 10:51 PM
 *
 * (c) ontometrics 2017, All Rights Reserved
 */

val log = KotlinLogging.logger {}

object MarkdownSpec : Spek({
    describe("given some markdown text"){
        val src = "Some *Markdown*"
        val flavour = CommonMarkFlavourDescriptor()
        val parsedTree = MarkdownParser(flavour).buildMarkdownTreeFromString(src)
        val html = HtmlGenerator(src, parsedTree, flavour).generateHtml()
        on("attempting to generate HTML"){
            it("should output what we expect"){
                assertEquals(html, "<body><p>Some <em>Markdown</em></p></body>")
            }
        }
    }
})