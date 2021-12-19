package com.nikitabolshakov.historyscreen.presentation.view.activity

import android.os.Bundle
import com.nikitabolshakov.core.presentation.view.activity.base.BaseActivity
import com.nikitabolshakov.historyscreen.R
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.historyscreen.databinding.ActivityHistoryBinding
import com.nikitabolshakov.historyscreen.domain.interactor.HistoryInteractor
import com.nikitabolshakov.historyscreen.presentation.adapter.HistoryActivityAdapter
import com.nikitabolshakov.historyscreen.presentation.viewModel.HistoryActivityViewModel
import com.nikitabolshakov.model.DataModel
import com.nikitabolshakov.utils.makeGone
import com.nikitabolshakov.utils.makeVisible
import com.nikitabolshakov.utils.ui.AlertDialogFragment
import org.koin.android.scope.currentScope

private const val DIALOG_FRAGMENT_TAG = "74a54328-5d62-46bf-ab6b-cbf5d8c79522"

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

    override fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.loadingFrameLayout.makeGone()
                appState.data?.let {
                    if (it.isEmpty()) {
                        showAlertDialog(
                            getString(R.string.dialog_tittle_sorry),
                            getString(R.string.empty_server_response_on_success)
                        )
                    } else {
                        setDataToAdapter(it)
                    }
                }
            }
            is AppState.Loading -> {
                binding.loadingFrameLayout.makeVisible()
            }
            is AppState.Error -> {
                binding.loadingFrameLayout.makeGone()
                showAlertDialog(
                    getString(R.string.error_stub),
                    appState.error.message
                )
            }
        }
    }

    private fun showAlertDialog(title: String?, message: String?) {
        AlertDialogFragment.newInstance(title, message)
            .show(supportFragmentManager, DIALOG_FRAGMENT_TAG)
    }

    override fun setDataToAdapter(data: List<DataModel>) {
        adapter.setData(data)
    }
}