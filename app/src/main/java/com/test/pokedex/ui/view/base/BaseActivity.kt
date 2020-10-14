package com.test.pokedex.ui.view.base

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import kotlinx.android.synthetic.main.progress_layout.*

/**
 * A Base Activity with some useful methods to manage "loading" state
 */
open class BaseActivity : AppCompatActivity() {

    fun loading(isLoading: Boolean) {
        loadingView?.isVisible = isLoading
    }

    override fun onBackPressed() {
        if (loadingView?.isVisible == false)
            super.onBackPressed()
    }

}