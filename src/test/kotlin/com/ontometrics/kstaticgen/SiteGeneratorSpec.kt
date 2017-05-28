package com.ontometrics.kstaticgen

import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on
import kotlin.test.assertEquals

/**
 *
 *
 * User: Rob
 * Date: 5/27/17
 * Time: 7:28 PM
 *
 * (c) ontometrics 2017, All Rights Reserved
 */
class SiteGeneratorSpec : Spek({
    describe("should be able to write a simple file out to the site directory"){
        on("attempting to generate"){
            val generator = SiteGenerator()
            generator.build()
            it("should put my html file in output"){
                assertEquals(1, 1)
            }
        }
    }
})