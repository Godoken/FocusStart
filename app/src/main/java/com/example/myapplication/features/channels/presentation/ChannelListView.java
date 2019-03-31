package com.example.myapplication.features.channels.presentation;

import com.example.myapplication.features.MvpView;
import com.example.myapplication.features.channels.domain.model.Channel;

import java.util.List;

interface ChannelListView extends MvpView {

    void showProgress();

    void hideProgress();

    void showChannelList(List<Channel> list);

    void showError(String message);

}