package com.melikeey.ui.track

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.melikeey.R
import com.melikeey.base.BaseViewHolder
import com.melikeey.databinding.ItemTrackBinding
import com.melikeey.model.Data
import java.util.*

class TrackAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var mTrackList: MutableList<Data> = ArrayList()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val itemBinding: ItemTrackBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_track, viewGroup, false)
        return BodyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, i: Int) {
        baseViewHolder.onBind(i)
    }

    fun addTrackList(trackList: List<Data?>?) {
        mTrackList.clear()
        mTrackList = trackList as MutableList<Data>
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mTrackList.size
    }

    inner class BodyViewHolder internal constructor(var mBinding: ItemTrackBinding) : BaseViewHolder(mBinding.root) {

        override fun clear() {}

        override fun onBind(position: Int) {
            super.onBind(position)
            val number = (position + 1).toString()

            mBinding.tvNumber.text = number
            mBinding.songHeader.text = mTrackList[position].title
            mBinding.songName.text = mTrackList[position].artist.name
        }

    }
}