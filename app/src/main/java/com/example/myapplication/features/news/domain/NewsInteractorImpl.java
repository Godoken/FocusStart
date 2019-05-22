package com.example.myapplication.features.news.domain;

import com.example.myapplication.exceptions.EmptyBodyException;
import com.example.myapplication.support.Carry;

import java.util.ArrayList;
import java.util.Arrays;

public class NewsInteractorImpl implements NewsInteractor {

    private String[] news_array;
    private ArrayList<String> stringArrayList;


    @Override
    public void convertToArrayList(String news, Carry<ArrayList<String>> carry) {

        stringArrayList = new ArrayList<>();

        news_array = news.split("<");
        stringArrayList.addAll(Arrays.asList(news_array));

        if (stringArrayList != null){
            carry.onSuccess(stringArrayList);
        } else {
            carry.onFailure(new EmptyBodyException());
        }

    }
}
