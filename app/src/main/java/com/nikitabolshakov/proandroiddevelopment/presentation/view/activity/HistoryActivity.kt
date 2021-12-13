package com.nikitabolshakov.proandroiddevelopment.presentation.view.activity

import android.os.Bundle
import com.nikitabolshakov.proandroiddevelopment.data.model.AppState
import com.nikitabolshakov.proandroiddevelopment.data.model.SkyengDataModel
import com.nikitabolshakov.proandroiddevelopment.databinding.ActivityHistoryBinding
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.HistoryInteractor
import com.nikitabolshakov.proandroiddevelopment.presentation.adapters.HistoryActivityAdapter
import com.nikitabolshakov.proandroiddevelopment.presentation.view.activity.base.BaseActivity
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModels.HistoryActivityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

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
        val historyViewModel: HistoryActivityViewModel by viewModel()
        viewModel = historyViewModel
        viewModel.subscribe().observe(this@HistoryActivity, { renderData(it) })
    }

    private fun initViews() {
        binding.historyActivityRecyclerview.adapter = adapter
    }
}