package com.example.sashi.tabviewtest;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class NewsFragment extends Fragment {

    private ImageView banner;
    private RecyclerView todayNews;
    private RecyclerView yesterdayNews;
    private RecyclerView previousNews;

    private List<News> newsList = new ArrayList<>();

    public NewsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNews();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

//        banner = rootView.findViewById(R.id.banner);
        todayNews = rootView.findViewById(R.id.today_news);
        yesterdayNews = rootView.findViewById(R.id.yesterday_news);
        previousNews = rootView.findViewById(R.id.previous_news);

        NewsAdapter adapter = new NewsAdapter(getContext(), newsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        todayNews.setLayoutManager(layoutManager);
        todayNews.setAdapter(adapter);

        return rootView;
    }

    private void initNews() {
        News news = new News("http://file.ituring.com.cn/SmallAvatar/00534955601aaa08619f", "fnatic to play at PLG Grand Slam", "3 hours ago");
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
    }
}
