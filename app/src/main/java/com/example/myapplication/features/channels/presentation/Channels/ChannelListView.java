package com.example.myapplication.features.channels.presentation.Channels;

import com.example.myapplication.features.InterfaceView;
import com.example.myapplication.features.channels.domain.model.Channel;

import java.util.List;

interface ChannelListView extends InterfaceView {

    void showProgress();

    void hideProgress();

    void showChannelList(List<Channel> list);

    void showError(String message);

    void showNews(String news);

    void createChannel();

    void deleteChannel(Channel channel);

    void startAppSettings();

    void openQuitDialog();

    //void onRestartView();

    //void createChannelFromDeepLink(String url);

    //void checkDeepLink();
}