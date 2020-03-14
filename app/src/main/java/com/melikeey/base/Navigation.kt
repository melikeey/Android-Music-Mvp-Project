package com.melikeey.base

import android.content.Intent
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

interface Navigation {
    fun openActivity(activity: Class<*>?, shouldFinishPrevious: Boolean)
    fun openActivity(intent: Intent?)
    fun openFragment(fragment: BaseFragment?, transaction: BaseNavigator.Transaction?, addToBackStack: Boolean?)
    fun openChildFragment(baseFragment: BaseFragment?, selectedFragment: BaseFragment?, @IdRes containerId: Int)
    val fragments: List<Fragment?>?
    fun popBackStack()
    fun popBackStackImmediate()
}