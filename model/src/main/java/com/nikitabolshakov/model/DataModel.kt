package com.nikitabolshakov.model

data class DataModel(
    val text: String = "",
    val meanings: List<Meaning> = listOf()
)

data class Meaning(
    val translatedMeaning: TranslatedMeaning = TranslatedMeaning(),
    val imageUrl: String = ""
)

data class TranslatedMeaning(val translatedMeaning: String = "")