package com.github.dant3.eshop.ui.search

import com.github.dant3.eshop.api.Page
import com.github.dant3.eshop.api.ShoppingApi
import com.github.dant3.eshop.model.ItemSummary
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.run

class SearchInteractor(private val shoppingApi: ShoppingApi) : Search.Interactor {
    suspend override fun loadNextPage(searchQuery: String?, pageIndex: Int): Page<ItemSummary> = run(CommonPool) {
        when {
            searchQuery == null || searchQuery.isEmpty() -> shoppingApi.getFeaturedItems(pageIndex)
            else -> shoppingApi.findItems(searchQuery, pageIndex)
        }
    }

    suspend override fun performSearch(searchQuery: String?): Page<ItemSummary> = run(CommonPool) {
        when {
            searchQuery == null || searchQuery.isEmpty() -> shoppingApi.getFeaturedItems()
            else -> shoppingApi.findItems(searchQuery)
        }
    }
}