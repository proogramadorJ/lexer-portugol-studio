package com.pedrodev

class Scanner {

    var tokens: Array<Token> = emptyArray()
    var pos: Int = 0
    var sourceCode: String

    constructor(source: String) {
        sourceCode = source
    }

    fun scanTokens(): Array<Token> {
        val currentChar = sourceCode[pos]

        

        return tokens
    }


}