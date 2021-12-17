package com.nikitabolshakov.historyscreen.presentation.view.activity

import android.os.Bundle
import com.nikitabolshakov.core.presentation.view.activity.base.BaseActivity
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.model.SkyengDataModel
import com.nikitabolshakov.historyscreen.databinding.ActivityHistoryBinding
import com.nikitabolshakov.historyscreen.domain.interactor.HistoryInteractor
import com.nikitabolshakov.historyscreen.presentation.adapter.HistoryActivityAdapter
import com.nikitabolshakov.historyscreen.presentation.viewModel.HistoryActivityViewModel
import org.koin.android.scope.currentScope

class HistoryActivity : BaseActivity<AppState, HistoryInteractor>() {

    private lateinit var binding: ActivityHistoryBinding

    override lateinit var viewModel: HistoryActivityViewModel

    private val adapter: HistoryActivityAdapter by lazy { HistoryActivityAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        iniViewModel()
        initViews()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getData("", false)
    }

    override fun setDataToAdapter(data: List<SkyengDataModel>) {
        adapter.setData(data)
    }

    private fun iniViewModel() {
        if (binding.historyActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val historyViewModel: HistoryActivityViewModel by currentScope.inject()
        viewModel = historyViewModel
        viewModel.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}