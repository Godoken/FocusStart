package com.example.myapplication.features.news.presentation;

import com.example.myapplication.features.InterfaceView;
import com.example.myapplication.features.channels.domain.model.Channel;

import java.util.ArrayList;
import java.util.List;

public interface NewsListView extends InterfaceView {

    void showProgress();

    void hideProgress();

    void showNewsList(ArrayList<String> stringArrayList);

    void showError(String message);

    void showNewsNet(String news);
}
