package com.example.myapplication.features.channels.domain;

import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;

public interface ChannelsInteractor {

    void loadChannels(Carry<List<Channel>> carry);

    void showNews(String url, Carry<String> carry);

    void createChannel(Channel channel, Carry<Channel> carry);

    void deleteChannel(String url, Carry<Success> carry);
}
