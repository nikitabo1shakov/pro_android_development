package com.nikitabolshakov.proandroiddevelopment.view.base

import androidx.appcompat.app.AppCompatActivity
import com.nikitabolshakov.proandroiddevelopment.model.data.AppState

abstract class BaseActivity<T : AppState> : AppCompatActivity() {

    // В каждой Активити будет своя ViewModel, которая наследуется от BaseViewModel.
    abstract val model: BaseViewModel<T>


    // Каждая Активити будет отображать какие-то данные в соответствующем состоянии.
    abstract fun renderData(appState: T)
}