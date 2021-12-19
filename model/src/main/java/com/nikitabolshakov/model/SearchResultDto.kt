package com.nikitabolshakov.model

import com.google.gson.annotations.SerializedName

private const val GSON_KEY_TEXT = "text"
private const val GSON_KEY_MEANINGS = "meanings"
private const val GSON_KEY_TRANSLATION = "translation"
private const val GSON_KEY_IMAGE_URL = "imageUrl"
private const val GSON_KEY_TRANSLATION_TEXT = "text"

class SearchResultDto(
    @SerializedName(GSON_KEY_TEXT) val text: String?,
    @SerializedName(GSON_KEY_MEANINGS) val meanings: List<MeaningsDto>?
)

class MeaningsDto(
    @SerializedName(GSON_KEY_TRANSLATION) val translation: TranslationDto?,
    @SerializedName(GSON_KEY_IMAGE_URL) val imageUrl: String?
)

class TranslationDto(@SerializedName(GSON_KEY_TRANSLATION_TEXT) val translation: String?)