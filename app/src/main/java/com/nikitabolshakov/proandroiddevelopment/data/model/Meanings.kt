package com.nikitabolshakov.proandroiddevelopment.data.model

import com.google.gson.annotations.SerializedName

private const val VALUE_TRANSLATION = "translation"
private const val VALUE_IMAGE_URL = "imageUrl"

class Meanings(
    @SerializedName(VALUE_TRANSLATION) val translation: Translation?,
    @SerializedName(VALUE_IMAGE_URL) val imageUrl: String?
)