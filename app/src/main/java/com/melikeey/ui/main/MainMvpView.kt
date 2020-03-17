package com.melikeey.ui.main

import androidx.appcompat.widget.Toolbar
import com.melikeey.base.MvpView

interface MainMvpView : MvpView {
    val toolbar: Toolbar?
}