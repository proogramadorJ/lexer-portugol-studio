package com.pedrodev

import java.io.File

fun main() {

    val codeFile = File("C:\\Desenvolvimento\\Projetos\\lexer-portugol-studio\\src\\test\\math.portugol")
    val codeText = codeFile.readText(Charsets.UTF_8)
    val scanner = Scanner(codeText)

    val tokens = scanner.scanTokens()

    for(token in tokens){
        println("Token ${token.type} lexema ${token.lexeme}")
        println("linha ${token.line} coluna ${token.column}\n")

    }

}
