package com.example.myapplication.features.channels.domain.model;

import java.util.ArrayList;

final public class Channel {

    private String name;
    private ArrayList<String> news;

    public Channel(String name, ArrayList<String> news){

        this.name = name;
        this.news = news;

    }

    public String getName(){
        return this.name;
    }

    public ArrayList<String> getNews(){
        return this.news;
    }

}
