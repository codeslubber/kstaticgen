package com.ontometrics.kstaticgen

import org.jetbrains.ktor.content.default
import org.jetbrains.ktor.content.files
import org.jetbrains.ktor.content.static
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.jetty.Jetty
import org.jetbrains.ktor.routing.routing

/**
 *
 *
 * User: Rob
 * Date: 5/27/17
 * Time: 8:02 PM
 *
 * (c) ontometrics 2017, All Rights Reserved
 */
class TestServer {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            embeddedServer(Jetty, 8080) {
                routing {
                    static {
                        default("index.html")
                        files("site")
                    }
                }
            }.start(wait = true)
        }
    }
}