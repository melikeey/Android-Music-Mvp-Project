package com.musiccomponent.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.musiccomponent.R;
import com.squareup.picasso.Picasso;

public class MusicAlbumView extends CardView {

    View rootView;
    TextView tvName, tvSong;
    ImageView ivPhoto;

    String name, song;

    String photo;

    public MusicAlbumView(Context context) {
        super(context, null);

        init(context);
    }

    public MusicAlbumView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
    }

    public MusicAlbumView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    public void init(Context context) {

        rootView = inflate(context, R.layout.music_album_item, this);

        tvName = rootView.findViewById(R.id.tv_name);
        tvSong = rootView.findViewById(R.id.tv_song);
        ivPhoto = rootView.findViewById(R.id.tv_photo);
    }

    public void setAlbumView(String name, String song, String photo) {

        this.name = name;
        this.song = song;
        this.photo = photo;

        setAlbumText();
    }


    private void setAlbumText() {


        tvName.setText(name);
        tvSong.setText(song);

        if (photo != null) {
            Picasso.get().load(photo).into(ivPhoto);

        }
    }
}