package com.github.dant3.eshop.ui

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import com.github.dant3.eshop.ui.util.ActivityResult
import com.github.dant3.eshop.ui.util.AsyncActivityRequestProcessor

open class BaseActivity : AppCompatActivity() {
    private val asyncActivityRequestProcessor by lazy { AsyncActivityRequestProcessor(this) }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        asyncActivityRequestProcessor.onActivityResult(requestCode, resultCode, data)
    }

    suspend fun startActivityForResultAsync(intent: Intent, requestCode: Int): ActivityResult =
            asyncActivityRequestProcessor.startActivityForResult(intent, requestCode)
}