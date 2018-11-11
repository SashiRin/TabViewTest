package com.example.sashi.tabviewtest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerViewNews;

    private List<News> newsList = new ArrayList<>();
    private List<News> newsListCopy = new ArrayList<>();

    private NewsAdapter adapter;

    private SwipeRefreshLayout swipeRefreshLayout;

    public NewsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initNews();
    }

    @Override
    public void onRefresh() {
        new MyTask().execute();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerViewNews = rootView.findViewById(R.id.recyclerview_news);
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerViewNews.setLayoutManager(layoutManager);
        adapter = new NewsAdapter(getContext(), newsList);
        recyclerViewNews.setAdapter(adapter);

        return rootView;
    }

    private void initNews() {
        new MyTask().execute();
    }

    private class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Log.d("Jsoup", "refresh start");
                newsListCopy.clear();

                String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/68.0.3440.106 Safari/537.36";
                Document doc = Jsoup.connect("https://hltv.org/").userAgent(userAgent).get();

                Element divIndex = doc.selectFirst("div.index");
                Element bigImageNews = divIndex.selectFirst("a.big-image-news");
//                Log.d("Jsoup", bigImageNews.toString());
                String bannerNewsUrl = bigImageNews.attr("href");
                String bannerPictureUrl = bigImageNews.getElementsByTag("img").attr("src");
                Log.d("Jsoup", bannerPictureUrl);
                Elements news = divIndex.select("a.newsline");
                for (Element e : news) {
//                    Log.d("Jsoup", e.select("div.newstext").text());
                    String title = e.select("div.newstext").text();
                    String regionGifUrl = e.select("img.newsflag").attr("src");
                    newsListCopy.add(new News(regionGifUrl, title, ""));
                }
//                Log.d("Jsoup", news.toString());
//                Log.d("Jsoup", divIndex.toString());
                Log.d("Jsoup", "refresh finish");

            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            newsList.clear();
            newsList.addAll(newsListCopy);
            adapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
    }
}