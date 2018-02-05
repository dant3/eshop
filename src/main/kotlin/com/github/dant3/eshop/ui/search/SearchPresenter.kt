package com.github.dant3.eshop.ui.search

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import com.github.dant3.eshop.PaginationController
import com.github.dant3.eshop.SimplePaginationController
import com.github.dant3.eshop.api.ApiException
import com.github.dant3.eshop.api.Page
import com.github.dant3.eshop.model.ItemSummary
import com.github.dant3.eshop.ui.util.Failure
import com.github.dant3.eshop.ui.util.Success
import com.github.dant3.eshop.ui.util.Try
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.anko.coroutines.experimental.asReference
import java.io.IOException

class SearchPresenter(lifecycleOwner: LifecycleOwner,
                      private val interactor: Search.Interactor,
                      private val router: Search.Router) : Search.Presenter {
    override val items: MutableLiveData<List<ItemSummary>> = MutableLiveData<List<ItemSummary>>().apply { value = emptyList() }
    override val searchInProgress: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = false }
    override val searchQuery: MutableLiveData<String> = MutableLiveData()
    override val lastError: MutableLiveData<Search.Error> = MutableLiveData()
    private var paginationController: PaginationController? = null

    init {
        searchQuery.observe(lifecycleOwner, Observer {
            onSearchQuerySubmitted(it)
        })
    }

    override fun onScrolledToLastItem() {
        paginationController?.run {
            if (isNextPageAvailable() && !isNextPageLoading()) {
                val nextPageIndex = nextPageIndex()
                val paginatedQuery = searchQuery.value
                loadPage(this, paginatedQuery, nextPageIndex)
            }
        }
    }

    private fun loadPage(paginationController: PaginationController, paginatedQuery: String?, pageIndex: Int) {
        val thisRef = this.asReference()
        paginationController.monitorPageLoading {
            async(UI) {
                val nextPage = Try.coroutine { interactor.loadNextPage(paginatedQuery, pageIndex) }
                with(thisRef()) {
                    when (nextPage) {
                        is Success<Page<ItemSummary>> ->
                            items.value = items.value!! + nextPage.value.items
                        is Failure -> {
                            lastError.value = interpretSearchFailure(nextPage.error)
                        }
                    }
                }
            }
        }
    }

    private fun onSearchQuerySubmitted(searchQuery: String?) {
        searchInProgress.value = true
        val thisRef = this.asReference()

        launch (UI) {
            val  searchResults = Try.coroutine { interactor.performSearch(searchQuery) }
            with (thisRef()) {
                when (searchResults) {
                    is Success<Page<ItemSummary>> -> {
                        items.value = searchResults.value.items
                        paginationController = SimplePaginationController(searchResults.value.totalPages, searchResults.value.index)
                    }
                    is Failure -> {
                        lastError.value = interpretSearchFailure(searchResults.error)
                    }
                }
                searchInProgress.value = false
            }
        }
    }

    override fun onItemClicked(itemSummary: ItemSummary) {
        router.goToItemSummary(itemSummary)
    }

    override fun onVoiceQueryInputClicked() {
        val thisRef = this.asReference()
        launch (UI) {
            val voiceInput = Try.coroutine { router.startVoiceSearchQueryInput() }
            with(thisRef()) {
                when (voiceInput) {
                    is Success<String> -> searchQuery.value = voiceInput.value
                    is Failure -> if (voiceInput.error is Search.VoiceInputIsNotSupported) {
                        lastError.value = Search.Error.VoiceInputIsNotSupported
                    } else {
                        throw IllegalStateException("Unexpected error caught", voiceInput.error)
                    }
                }
            }
        }
    }

        private fun interpretSearchFailure(failure: Throwable) = when (failure) {
            is ApiException -> Search.Error.ServiceIsNotAvailable
            is IOException -> Search.Error.NetworkConnectionProblems
            else -> throw IllegalStateException("Unexpected error", failure)
        }
}