package com.github.dant3.eshop.ui.search

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Toast
import com.github.dant3.eshop.EShopApplication
import com.github.dant3.eshop.model.ItemSummary
import com.github.dant3.eshop.ui.BaseActivity
import com.github.dant3.eshop.ui.util.localeCompat
import com.github.dant3.eshop.R
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.run


class SearchItemActivity : BaseActivity(), Search.Router {
    private val speechInputRequestId = 10001

    private val shoppingApi by lazy { EShopApplication.eShopComponent.api }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val interactor = SearchInteractor(shoppingApi)
        val presenter = SearchPresenter(this, interactor, this)
        SearchUi.setContentViewForActivity(this, presenter)
    }

    override fun goToItemSummary(itemSummary: ItemSummary) {
        Toast.makeText(this, "TODO: add item summary screen", Toast.LENGTH_LONG).show()
    }

    @Throws(Search.VoiceInputIsNotSupported::class)
    suspend override fun startVoiceSearchQueryInput(): String = run(UI) {
        val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, resources.configuration.localeCompat)
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, getString(R.string.search))
        try {
            val result = startActivityForResultAsync(intent, speechInputRequestId)
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                result.data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)[0]
            } else {
                throw IllegalStateException("Unsupported result of voice input: $result")
            }
        } catch (a: ActivityNotFoundException) {
            throw Search.VoiceInputIsNotSupported()
        }
    }
}
