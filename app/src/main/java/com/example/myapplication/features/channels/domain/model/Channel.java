package com.example.myapplication.features.channels.domain.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
final public class Channel {

    private String name;
    @NonNull
    @PrimaryKey private String url;
    private String news;

    public Channel(String name, String news, String url){

        this.name = name;
        this.news = news;
        this.url = url;

    }

    public String getName(){
        return name;
    }

    public String getNews(){
        return news;
    }

    public String getUrl() {
        return url;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setUrl(String url){
        this.url = url;
    }

    public void setNews(String news){
        this.news = news;
    }
}
