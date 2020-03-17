package com.melikeey.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

    var currentPosition = 0
        private set

    protected abstract fun clear()

    open fun onBind(position: Int) {
        currentPosition = position
        clear()
    }
}