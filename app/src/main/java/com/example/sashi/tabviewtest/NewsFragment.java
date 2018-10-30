package com.example.sashi.tabviewtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NewsFragment extends Fragment {

    private ImageView banner;
    private RecyclerView todayNews;
    private RecyclerView yesterdayNews;
    private RecyclerView previousNews;

    private BannerNews bannerNews;
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

        banner = rootView.findViewById(R.id.banner);
        todayNews = rootView.findViewById(R.id.today_news);
        yesterdayNews = rootView.findViewById(R.id.yesterday_news);
        previousNews = rootView.findViewById(R.id.previous_news);

        GlideApp.with(getContext())
                .load(bannerNews.getBannerPictureUrl())
                .into(banner);

        NewsAdapter adapter = new NewsAdapter(getContext(), newsList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        todayNews.setLayoutManager(layoutManager);
        todayNews.setAdapter(adapter);

        return rootView;
    }

    private void initNews() {
        bannerNews.setBannerPictureUrl();
        bannerNews.setBannerNewsUrl();
        News news = new News("https://static.hltv.org/images/bigflags/30x20/US.gif", "fnatic to play at PLG Grand Slam", "3 hours ago");
        newsList.add(news);
        newsList.add(news);
        newsList.add(news);
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                newsList.clear();

                String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";
                Document doc = Jsoup.connect("https://hltv.org/").userAgent(userAgent).get();

                Elements newslines = doc.getElementsByClass("newsline");
                for (Element newsline : newslines) {
                    Elements imgTag = newsline.getElementsByTag("img");
                    String regionUrl = imgTag.attr("src");
                    Elements titleTags = newsline.getElementsByClass("newstext");
                    String linkHref = newsline.attr("href");
                    String title =


                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
}

class BannerNews {
    private String bannerPictureUrl;
    private String bannerNewsUrl;

    BannerNews() {
    }

    public String getBannerNewsUrl() {
        return bannerNewsUrl;
    }

    public String getBannerPictureUrl() {
        return bannerPictureUrl;
    }

    public void setBannerNewsUrl(String bannerNewsUrl) {
        this.bannerNewsUrl = bannerNewsUrl;
    }

    public void setBannerPictureUrl(String bannerPictureUrl) {
        this.bannerPictureUrl = bannerPictureUrl;
    }
}