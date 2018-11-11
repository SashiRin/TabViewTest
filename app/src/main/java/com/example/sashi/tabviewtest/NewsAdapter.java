package com.example.sashi.tabviewtest;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import io.github.luizgrp.sectionedrecyclerviewadapter.SectionParameters;
import io.github.luizgrp.sectionedrecyclerviewadapter.StatelessSection;

public class NewsAdapter extends StatelessSection {

    private List<News> mNewsList;

    private Context mContext;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView regionImage;
        TextView title;
        TextView timeStamp;

        public ViewHolder(View view) {
            super(view);
            regionImage = view.findViewById(R.id.region_image);
            title = view.findViewById(R.id.news_title);
            timeStamp = view.findViewById(R.id.news_time_stamp);
        }
    }

    public NewsAdapter(List<News> newsList) {
        super(SectionParameters.builder()
                .itemResourceId(R.layout.news_item)
                .headerResourceId(R.layout.news_header)
                .build());
        mNewsList = newsList;
    }

    @Override
    public int getContentItemsTotal() {
        return mNewsList.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = mNewsList.get(position);
        holder.title.setText(news.getTitle());
        holder.timeStamp.setText(news.getTimeStamp());
//        Glide.with(mContext).load(news.getRegionGifUrl()).into(holder.regionImage);
//        holder.regionImage.setImageResource(R.drawable.ic_launcher_background);
//        GlideApp.with(mContext).load(news.getRegionGifUrl()).into(holder.regionImage);
        GlideApp.with(mContext)
                .load(news.getRegionGifUrl())
                .override(60, 40)
//                .centerCrop()
//                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.regionImage);
    }
}