package com.example.myapplication.features.news.presentation;

import com.example.myapplication.features.InterfaceView;

import java.util.ArrayList;

public interface NewsListView extends InterfaceView {

    void showProgress();

    void hideProgress();

    void showNewsList(ArrayList<String> stringArrayList);

    void showError(String message);

}
