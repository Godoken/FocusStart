package com.example.myapplication.features.news.presentation;

import com.example.myapplication.features.InterfaceView;
import com.example.myapplication.features.channels.domain.model.Channel;

import java.util.List;

public interface NewsListView extends InterfaceView {

    void showProgress();

    void hideProgress();

    void showNewsList(List<String> list);

    void showError(String message);

    void showNewsNet(String news);
}
