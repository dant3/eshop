package com.github.dant3.eshop.ui.search

import android.arch.lifecycle.Observer
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.github.dant3.eshop.R
import com.github.dant3.eshop.ui.GridSpacingItemDecoration
import com.github.dant3.eshop.ui.util.dpToPx
import com.lapism.searchview.SearchView


object SearchUi {
    fun setContentViewForActivity(activity: AppCompatActivity, presenter: Search.Presenter) {
        with(activity) {
            activity.setContentView(R.layout.activity_search_item)
            setSupportActionBar(findViewById<Toolbar>(R.id.toolbar))

            val itemsRecyclerView = findViewById<RecyclerView>(R.id.items_list)
            val searchView = findViewById<SearchView>(R.id.search_view)
            val itemsAdapter = ItemsAdapter()

            val layoutManager = GridLayoutManager(this, 2)
            val spacingItemDecorator = GridSpacingItemDecoration(layoutManager, dpToPx(10))

            itemsRecyclerView.layoutManager = layoutManager
            itemsRecyclerView.addItemDecoration(spacingItemDecorator)
            itemsRecyclerView.itemAnimator = DefaultItemAnimator()
            itemsRecyclerView.adapter = itemsAdapter
            itemsRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (isLastItemShown()) {
                        presenter.onScrolledToLastItem()
                    }
                }

                private fun isLastItemShown(): Boolean {
                    val visibleItemsCount = layoutManager.childCount
                    val totalItemsCount = layoutManager.itemCount
                    val pastVisibleItemsCount = layoutManager.findFirstVisibleItemPosition()
                    return visibleItemsCount + pastVisibleItemsCount >= totalItemsCount
                }
            })

            searchView.hint = resources.getString(R.string.search)
            searchView.setNavigationIcon(R.drawable.ic_search)
            searchView.setNavigationIconClickListener {
                searchView.setQuery(searchView.query, /*submit =*/ true)
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    presenter.searchQuery.value = query
                    searchView.clearFocus()
                    return true
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    return false
                }
            })

            // TODO: add search history support
            searchView.setVoice(true)
            searchView.setOnVoiceIconClickListener {
                presenter.onVoiceQueryInputClicked()
            }

            // --- bind to presenter ---
            presenter.items.observe(activity, Observer {
                list -> itemsAdapter.items = list ?: emptyList()
            })
            presenter.searchInProgress.observe(activity, Observer {
                searchView.showProgress(it ?: false)
            })
            presenter.searchQuery.observe(activity, Observer {
                searchView.setQuery(it ?: "", false)
            })
            presenter.lastError.observe(activity, Observer {
                if (it != null) {
                    presentErrorToUser(activity, it)
                }
            })
            itemsAdapter.onItemClicked.observe(activity, Observer {
                if (it != null) {
                    presenter.onItemClicked(it)
                }
            })

            if (presenter.items.value?.isEmpty() != false) {
                // submitting search view should force loading of content for a query
                // this seems like a hack, but this way it will display progress without more tweaks
                searchView.setQuery(searchView.query.toString(), true)
            }
        }
    }

    private fun presentErrorToUser(context: Context, error: Search.Error) {
        val message = when (error) {
            Search.Error.VoiceInputIsNotSupported -> R.string.voice_input_not_supported
            Search.Error.NetworkConnectionProblems -> R.string.network_connectivity_issues
            Search.Error.ServiceIsNotAvailable -> R.string.service_issues
        }
        AlertDialog.Builder(context)
                .setMessage(message)
                .setNeutralButton(android.R.string.ok, { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                })
                .create()
                .show()
    }

    private fun SearchView.showProgress(show: Boolean) {
        when (show) {
            true -> showProgress()
            false -> hideProgress()
        }
    }
}