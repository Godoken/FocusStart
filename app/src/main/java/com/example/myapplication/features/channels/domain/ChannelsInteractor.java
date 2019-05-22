package com.example.myapplication.features.channels.domain;

import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;

import java.util.List;

public interface ChannelsInteractor {

    void loadChannels(Carry<List<Channel>> carry);

    void showNews(String url, Carry<String> carry);

    void createChannel(Channel channel, Carry<Channel> carry);

    void deleteChannel(Channel channel, Carry<Success> carry);
}
