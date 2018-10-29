package com.example.sashi.tabviewtest;

public class News {
    private String regionGifUrl;
    private String title;
    private String timeStamp;

    public News() {
    }

    public News(String regionGifUrl, String title, String timeStamp) {
        this.regionGifUrl = regionGifUrl;
        this.title = title;
        this.timeStamp = timeStamp;
    }

    public String getRegionGifUrl() {
        return regionGifUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getTimeStamp() {
        return timeStamp;
    }
}
