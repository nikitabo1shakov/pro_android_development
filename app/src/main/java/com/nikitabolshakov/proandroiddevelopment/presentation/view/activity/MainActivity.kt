package com.nikitabolshakov.proandroiddevelopment.presentation.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikitabolshakov.core.presentation.view.activity.base.BaseActivity
import com.nikitabolshakov.proandroiddevelopment.R
import com.nikitabolshakov.model.AppState
import com.nikitabolshakov.model.SkyengDataModel
import com.nikitabolshakov.historyscreen.presentation.view.activity.HistoryActivity
import com.nikitabolshakov.proandroiddevelopment.databinding.ActivityMainBinding
import com.nikitabolshakov.proandroiddevelopment.domain.interactor.MainInteractor
import com.nikitabolshakov.proandroiddevelopment.presentation.adapter.MainActivityAdapter
import com.nikitabolshakov.proandroiddevelopment.presentation.view.fragment.SearchDialogFragment
import com.nikitabolshakov.proandroiddevelopment.presentation.viewModel.MainActivityViewModel
import com.nikitabolshakov.proandroiddevelopment.utils.convertMeaningsToString
import com.nikitabolshakov.utils.network.isOnline
import org.koin.android.scope.currentScope

private const val BOTTOM_SHEET_FRAGMENT_DIALOG_TAG = "74a54328-5d62-46bf-ab6b-cbf5fgt0-092395"

class MainActivity : BaseActivity<AppState, MainInteractor>() {

    private lateinit var binding: ActivityMainBinding

    override lateinit var viewModel: MainActivityViewModel

    private val adapter: MainActivityAdapter by lazy {
        MainActivityAdapter(onListItemClickListener)
    }

    private val fabClickListener: View.OnClickListener =
        View.OnClickListener {
            val searchDialogFragment = SearchDialogFragment.newInstance()
            searchDialogFragment.setOnSearchClickListener(onSearchClickListener)
            searchDialogFragment.show(supportFragmentManager, BOTTOM_SHEET_FRAGMENT_DIALOG_TAG)
        }

    private val onListItemClickListener: MainActivityAdapter.OnListItemClickListener =
        object : MainActivityAdapter.OnListItemClickListener {
            override fun onItemClick(data: SkyengDataModel) {
                startActivity(
                    DescriptionActivity.getIntent(
                        this@MainActivity,
                        data.text!!,
                        convertMeaningsToString(data.meanings!!),
                        data.meanings!![0].imageUrl
                    )
                )
            }
        }

    private val onSearchClickListener: SearchDialogFragment.OnSearchClickListener =
        object : SearchDialogFragment.OnSearchClickListener {
            override fun onClick(searchWord: String) {
                isNetworkAvailable = isOnline(applicationContext)
                if (isNetworkAvailable) {
                    viewModel.getData(searchWord, isNetworkAvailable)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initViews()
    }

    override fun setDataToAdapter(data: List<SkyengDataModel>) {
        adapter.setData(data)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.history_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_history -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initViewModel() {
        if (binding.mainActivityRecyclerview.adapter != null) {
            throw IllegalStateException("The ViewModel should be initialised first")
        }
        val mainActivityViewModel: MainActivityViewModel by currentScope.inject()
        viewModel = mainActivityViewModel
        viewModel.subscribe().observe(this@MainActivity, { renderData(it) })
    }

    private fun initViews() {
        with(binding) {
            searchFab.setOnClickListener(fabClickListener)
            mainActivityRecyclerview.layoutManager = LinearLayoutManager(applicationContext)
            mainActivityRecyclerview.adapter = adapter
        }
    }
}