package com.ontometrics.kstaticgen

/**
 *
 *
 * User: Rob
 * Date: 5/22/17
 * Time: 8:07 PM
 *
 * (c) ontometrics 2017, All Rights Reserved
 */

interface SourceTransformer {
    fun build(source:String) : String
}

class MarkdownProcessor : SourceTransformer {
    override fun build(source: String): String {
        return ""
    }

}