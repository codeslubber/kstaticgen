package com.ontometrics.kstaticgen

import java.nio.file.Paths

/**
 *
 *
 * User: Rob
 * Date: 5/27/17
 * Time: 7:02 PM
 *
 * (c) ontometrics 2017, All Rights Reserved
 */
class SiteGenerator {
    fun build() {
        val outputTestFile = Paths.get("site/index.html").toFile()

        outputTestFile.printWriter().use { out ->
            out.write(createHelloWorldPage())
        }

    }

    private fun  createHelloWorldPage(): String {
        val page = "<!doctype html><title>Hello</title><body>Hello World!</body>"
        return page
    }
}