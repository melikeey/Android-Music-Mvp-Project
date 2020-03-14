package com.melikeey.ui.album

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.melikeey.R
import com.melikeey.base.BaseNavigator
import com.melikeey.base.BaseViewHolder
import com.melikeey.databinding.ItemAlbumBinding
import com.melikeey.model.Data
import com.melikeey.ui.track.TrackFragment

class AlbumAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var albumFragment: AlbumFragment? = null
    private var albumList: List<Data>? = null
    private var artistName: String? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val itemBinding: ItemAlbumBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_album, viewGroup, false)
        return BodyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, i: Int) {
        baseViewHolder.onBind(i)
    }

    fun setFragment(fragment: AlbumFragment?) {
        albumFragment = fragment
    }

    fun addAlbum(data: List<Data>?, artistName: String?) {
        albumList = data
        this.artistName = artistName
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return albumList!!.size
    }

    inner class BodyViewHolder internal constructor(var mBinding: ItemAlbumBinding) : BaseViewHolder(mBinding.root) {
        override fun clear() {}

        override fun onBind(position: Int) {
            super.onBind(position)
            mBinding.mav.setAlbumView(albumList!![position].title, artistName, albumList!![position].cover_big)
            mBinding.root.setOnClickListener { view: View? -> albumFragment!!.mPresenter.openFragment(TrackFragment.newInstance(albumList!![position].id, albumList!![position].cover_big, albumList!![position].title, artistName), BaseNavigator.Transaction.REPLACE, true) }
        }

    }
}