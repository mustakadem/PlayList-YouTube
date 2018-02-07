package com.example.mustafa.playlist_youtube;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mustafa.playlist_youtube.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EntAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<Video> videosList;

    public EntAdapter(Activity activity, ArrayList<Video> list){
        this.activity = activity;
        this.videosList = list;
    }

    @Override
    public int getCount() {
        return videosList.size();
    }

    @Override
    public Object getItem(int i) {
        return videosList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.video_item,null);
        }

        Video video = videosList.get(i);

        TextView title = v.findViewById(R.id.textViewTitle);
        title.setText(video.getTitle());

        TextView author = v.findViewById(R.id.textViewAuthor);
        author.setText(video.getAuthor());

        TextView views = v.findViewById(R.id.textViewView);
        views.setText(video.getViews());

        ImageView imgVideo = v.findViewById(R.id.imageViewVideoItem);

        Picasso.with(v.getContext()).load(video.getImage()).into(imgVideo);


        return v;
    }
}
