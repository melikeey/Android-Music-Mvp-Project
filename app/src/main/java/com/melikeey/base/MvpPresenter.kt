package com.melikeey.base

import androidx.annotation.IdRes

/**
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
interface MvpPresenter<V : MvpView?> : Navigation {

    fun onViewPrepared()

    fun onAttach(mvpView: V)

    fun onDetach()

    override fun openActivity(activity: Class<*>?, shouldFinishPrevious: Boolean)

    override fun openFragment(fragment: BaseFragment?, transaction: BaseNavigator.Transaction?, addToBackStack: Boolean?)

    override fun openChildFragment(baseFragment: BaseFragment?, selectedFragment: BaseFragment?, @IdRes containerId: Int)

    override fun popBackStack()

    override fun popBackStackImmediate()
}