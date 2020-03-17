package com.melikeey.ui.track

import android.graphics.Color
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.melikeey.R
import com.melikeey.base.BaseNavigator
import com.melikeey.base.BaseViewHolder
import com.melikeey.databinding.ItemTrackBinding
import com.melikeey.model.Data
import com.melikeey.ui.player.PlayFragment
import java.io.IOException
import java.lang.Exception
import java.util.*

class TrackAdapter : RecyclerView.Adapter<BaseViewHolder>() {

    private var mTrackList: MutableList<Data> = ArrayList()

    private var trackFragment : TrackFragment? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BaseViewHolder {

        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val itemBinding: ItemTrackBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_track, viewGroup, false)
        return BodyViewHolder(itemBinding)
    }

    override fun onBindViewHolder(baseViewHolder: BaseViewHolder, i: Int) {
        baseViewHolder.onBind(i)
    }

    fun setFragment(fragment: TrackFragment?){
        trackFragment = fragment;
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

            val mp = MediaPlayer();

            mBinding.btnStart.setOnClickListener{ view : View? ->
                try {
                    mp.setDataSource(mTrackList[position].preview)
                    mp.prepare()
                    mp.start()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


            mBinding.btnStop.setOnClickListener{ _: View? ->
                try {

                    mBinding.btnStop.setBackgroundColor(Color.GRAY);
                    mBinding.btnStart.setBackgroundColor(Color.BLACK)
                    mp.stop()
                } catch (e: Exception) {

                    e.printStackTrace()
                }
            }

        }
    }
}