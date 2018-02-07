package com.github.dant3.eshop.ui.search

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.github.dant3.eshop.api.Page
import com.github.dant3.eshop.model.ItemSummary

interface Search {
    interface Presenter {
        val items: LiveData<List<ItemSummary>>
        val searchInProgress: LiveData<Boolean>
        val searchQuery: MutableLiveData<String>
        val lastError: LiveData<Error>

        fun onScrolledToLastItem()
        fun onItemClicked(itemSummary: ItemSummary)
        fun onVoiceQueryInputClicked()
        fun loadInitialData()
    }

    interface Interactor {
        suspend fun loadNextPage(searchQuery: String?, pageIndex: Int): Page<ItemSummary>
        suspend fun performSearch(searchQuery: String?): Page<ItemSummary>
    }

    interface Router {
        fun goToItemSummary(itemSummary: ItemSummary)
        @Throws(VoiceInputIsNotSupported::class) suspend fun startVoiceSearchQueryInput(): String
    }


    class VoiceInputIsNotSupported : Exception()

    enum class Error {
        NetworkConnectionProblems,
        ServiceIsNotAvailable,
        VoiceInputIsNotSupported
    }
}