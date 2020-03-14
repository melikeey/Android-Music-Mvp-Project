package com.melikeey.base

import com.melikeey.base.BaseFragment.NavigationIcon

interface ToolbarListener {
    fun hideToolbar()
    fun showToolbar()
    fun prepareToolbar(navIcon: NavigationIcon?, title: String?)
}