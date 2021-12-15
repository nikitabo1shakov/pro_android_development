package com.nikitabolshakov.proandroiddevelopment.utils

import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.model.Meanings
import com.nikitabolshakov.proandroiddevelopment.data.model.SkyengDataModel
import com.nikitabolshakov.proandroiddevelopment.data.room.HistoryEntity

fun parseOnlineSearchResults(appState: AppState): AppState =
    AppState.Success(mapResult(appState, true))

fun parseLocalSearchResults(appState: AppState): AppState =
    AppState.Success(mapResult(appState, false))

private fun mapResult(
    appState: AppState,
    isOnline: Boolean
): List<SkyengDataModel> {
    val newSearchResults = arrayListOf<SkyengDataModel>()
    when (appState) {
        is AppState.Success -> {
            getSuccessResultData(appState, isOnline, newSearchResults)
        }
        else -> {}
    }
    return newSearchResults
}

private fun getSuccessResultData(
    appState: AppState.Success,
    isOnline: Boolean,
    newDataModels: ArrayList<SkyengDataModel>
) {
    val skyengDataModel: List<SkyengDataModel> = appState.data as List<SkyengDataModel>
    if (skyengDataModel.isNotEmpty()) {
        if (isOnline) {
            for (searchResult in skyengDataModel) {
                parseOnlineResult(searchResult, newDataModels)
            }
        } else {
            for (searchResult in skyengDataModel) {
                newDataModels.add(SkyengDataModel(searchResult.text, arrayListOf()))
            }
        }
    }
}

private fun parseOnlineResult(
    skyengDataModel: SkyengDataModel,
    newDataModels: ArrayList<SkyengDataModel>
) {
    if (!skyengDataModel.text.isNullOrBlank() && !skyengDataModel.meanings.isNullOrEmpty()) {
        val newMeanings = arrayListOf<Meanings>()
        for (meaning in skyengDataModel.meanings) {
            if (meaning.translation != null && !meaning.translation.translation.isNullOrBlank()) {
                newMeanings.add(Meanings(meaning.translation, meaning.imageUrl))
            }
        }
        if (newMeanings.isNotEmpty()) {
            newDataModels.add(SkyengDataModel(skyengDataModel.text, newMeanings))
        }
    }
}

fun mapHistoryEntityToSearchResult(list: List<HistoryEntity>): List<SkyengDataModel> {
    val searchResult = ArrayList<SkyengDataModel>()
    if (!list.isNullOrEmpty()) {
        for (entity in list) {
            searchResult.add(SkyengDataModel(entity.word, null))
        }
    }
    return searchResult
}

fun convertDataModelSuccessToEntity(appState: AppState): HistoryEntity? {
    return when (appState) {
        is AppState.Success -> {
            val searchResult = appState.data
            if (searchResult.isNullOrEmpty() || searchResult[0].text.isNullOrEmpty()) {
                null
            } else {
                HistoryEntity(searchResult[0].text!!, null)
            }
        }
        else -> null
    }
}


fun convertMeaningsToString(meanings: List<Meanings>): String {
    var meaningsSeparatedByComma = String()
    for ((index, meaning) in meanings.withIndex()) {
        meaningsSeparatedByComma += if (index + 1 != meanings.size) {
            String.format("%s%s", meaning.translation?.translation, ", ")
        } else {
            meaning.translation?.translation
        }
    }
    return meaningsSeparatedByComma
}