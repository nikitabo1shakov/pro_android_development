package com.nikitabolshakov.proandroiddevelopment.data.model

import com.google.gson.annotations.SerializedName

private const val VALUE_TEXT = "text"

class Translation(@SerializedName(VALUE_TEXT) val translation: String?)