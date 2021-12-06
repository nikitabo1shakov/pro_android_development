package com.nikitabolshakov.proandroiddevelopment.data.model

import com.google.gson.annotations.SerializedName

private const val VALUE_TEXT = "text"
private const val VALUE_MEANINGS = "meanings"

class DataModel(
    @SerializedName(VALUE_TEXT) val text: String?,
    @SerializedName(VALUE_MEANINGS) val meanings: List<Meanings>?
)