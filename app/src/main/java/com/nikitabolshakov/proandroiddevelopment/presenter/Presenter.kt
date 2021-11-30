package com.nikitabolshakov.proandroiddevelopment.presenter

import com.nikitabolshakov.proandroiddevelopment.view.base.View
import com.nikitabolshakov.proandroiddevelopment.model.data.AppState

// На уровень выше находится презентер, который уже ничего не знает ни о контексте, ни о фреймворке.
interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)
    fun detachView(view: V)

    // Получение данных с флагом isOnline (из Интернета или нет)
    fun getData(word: String, isOnline: Boolean)
}