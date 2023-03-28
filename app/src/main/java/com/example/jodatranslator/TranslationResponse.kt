package com.example.jodatranslator

class TranslationResponse(val contents: Content) {

    data class Content(
        val translated: String,
        val text: String,
        val translation: String
    )
}