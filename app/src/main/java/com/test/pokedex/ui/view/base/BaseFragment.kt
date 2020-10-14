package com.test.pokedex.ui.view.base

import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.no_data_layout.*

/**
 * A Base Fragment with some useful method to manage loading state and empty view
 */
open class BaseFragment : Fragment() {

    fun loading(isLoading: Boolean) {
        (activity as BaseActivity).loading(isLoading)
    }

    fun showEmptyView(infoMessage: String? = null) {
        noDataView?.isVisible = true

        if (!infoMessage.isNullOrEmpty()) {
            noDataMessage.text = infoMessage
        }
    }

    fun hideEmptyView() {
        noDataView?.isVisible = false
    }

}