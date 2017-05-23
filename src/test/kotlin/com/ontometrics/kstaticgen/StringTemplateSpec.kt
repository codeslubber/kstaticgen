package com.ontometrics.kstaticgen

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import org.stringtemplate.v4.ST
import kotlin.test.assertEquals


/**
 *
 *
 * User: robwilliams
 * Date: 4/29/17
 * Time: 11:06 PM
 *
 * (c) ontometrics 2017, All Rights Reserved
 */

object StringTemplateSpec : Spek({
    describe("making a simple replacement"){
        on("having a template with a single field"){
            it("should be possible to replace that field"){
                val template = ST("Hello, <name>!")
                template.add("name", "World")
                val output = template.render()
                println(output)

                assertEquals(output, "Hello, World!")
            }
        }
    }
})