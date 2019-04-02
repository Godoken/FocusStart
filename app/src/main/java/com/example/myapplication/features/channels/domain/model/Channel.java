package com.example.myapplication.features.channels.domain.model;

import java.util.ArrayList;

final public class Channel {

    private String name;
    private String url;
    private ArrayList<String> news;

    public Channel(String name, ArrayList<String> news, String url){

        this.name = name;
        this.news = news;
        this.url = url;

    }

    public String getName(){
        return name;
    }

    public ArrayList<String> getNews(){
        return news;
    }

    public String getUrl() {
        return url;
    }
}
