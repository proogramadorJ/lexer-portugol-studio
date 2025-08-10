package com.pedrodev

class Scanner {

    val tokens = mutableListOf<Token>()
    var pos: Int = 0
    var sourceCode: String
    var currentLine = 1
    var currentColumn = 0

    constructor(source: String) {
        sourceCode = source
    }

    fun scanTokens(): List<Token> {
        while (pos < sourceCode.length) {
            val currentChar = sourceCode[pos]

            when (currentChar) {
                ',' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_VIRGULA, currentLine, currentColumn, ","))
                    currentColumn++
                }

                '{' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_ABRE_CHAVE, currentLine, currentColumn, "{"))
                    currentColumn++
                }

                '}' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_FECHA_CHAVE, currentLine, currentColumn, "}"))
                    currentColumn++
                }

                '(' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_ABRE_PARENTESE, currentLine, currentColumn, "("))
                    currentColumn++
                }

                ')' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_FECHA_PARENTESE, currentLine, currentColumn, ")"))
                    currentColumn++
                }

                '[' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_ABRE_COLCHETE, currentLine, currentColumn, "]"))
                    currentColumn++
                }

                ']' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_FECHA_COLCHETE, currentLine, currentColumn, "]"))
                    currentColumn++
                }

                ':' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_DOIS_PONTOS, currentLine, currentColumn, ":"))
                    currentColumn++
                }

                '&' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_BIT_AND, currentLine, currentColumn, "&"))
                    currentColumn++
                }

                '|' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_BIT_OR, currentLine, currentColumn, "|"))
                    currentColumn++
                }

                '~' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_BIT_NOT, currentLine, currentColumn, "~"))
                    currentColumn++
                }

                '<' -> {
                    if (next() == '<') {
                        tokens.add(Token(TokenType.TK_BIT_SHIFT, currentLine, currentColumn, "<<"))
                        pos += 2
                        currentColumn += 2
                    } else {
                        match('=', TokenType.TK_MENOR_OU_IGUAL, TokenType.TK_MENOR, '<')
                    }
                }

                '>' -> {
                    match('=', TokenType.TK_MAIOR_OU_IGUAL, TokenType.TK_MAIOR, '>')
                }

                '!' -> {
                    match('=', TokenType.TK_DIFERENTE, TokenType.TK_IGUAL, '!')
                }

                '=' -> {
                    match('=', TokenType.TK_IGUAL_IGUAL, TokenType.TK_IGUAL, '=')
                }

                '^' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_BIT_XOR, currentLine, currentColumn, "^"))
                    currentColumn++
                }

                '+' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_SOMA, currentLine, currentColumn, "+"))
                    currentColumn++
                }

                '-' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_SUBTRACAO, currentLine, currentColumn, "-"))
                    currentColumn++
                }

                '*' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_MULTPLICACAO, currentLine, currentColumn, "*"))
                    currentColumn++
                }

                '/' -> {
                    if (next() == '/') {
                        while (pos < sourceCode.length && sourceCode[pos] != '\n') {
                            pos++
                        }
                        if (pos < sourceCode.length) {
                            currentLine++
                            currentColumn = 1
                            pos++
                        }
                    } else {
                        pos++
                        tokens.add(Token(TokenType.TK_DIVISAO, currentLine, currentColumn, "/"))
                        currentColumn++

                    }
                }

                '%' -> {
                    pos++
                    tokens.add(Token(TokenType.TK_MODULO, currentLine, currentColumn, "%"))
                    currentColumn++
                }

                ' ' -> {
                    pos++
                    currentColumn++
                }

                '\n' -> {
                    pos++
                    currentLine++
                    currentColumn = 1
                }

                else -> {
                    throw RuntimeException("Simbolo não identificado na linha $currentLine coluna $currentColumn")
                }
            }

        }

        tokens.add(Token(TokenType.EOF, -1, -1, ""))
        return tokens
    }

    fun next(): Char {
        if (pos + 1 >= sourceCode.length) {
            return '\u0000'
        }
        return sourceCode[pos + 1]
    }

    fun match(c: Char, type1: TokenType, type2: TokenType, lexeme: Char) {
        if (next() == c) {
            tokens.add(Token(type1, currentLine, currentColumn, "" + lexeme + c))
            currentColumn += 2
            pos += 2
            return
        }
        tokens.add(Token(type2, currentLine, currentColumn, "" + lexeme))
        pos++
        currentColumn
    }

    fun isDigit(c: Char): Boolean {
        return c in '0'..'9'
    }

    private fun isAlphaNumeric(c: Char): Boolean {
        return isAlpha(c) || isDigit(c)
    }

    private fun isAlpha(c: Char): Boolean {
        return c in 'a'..'z' || c in 'A'..'Z' || c == '_'
    }
}