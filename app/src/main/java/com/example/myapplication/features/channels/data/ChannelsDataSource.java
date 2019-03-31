package com.example.myapplication.features.channels.data;

import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;

public interface ChannelsDataSource {

    void getChannels(Carry<List<Channel>> carry);

    void getChannel(String id, Carry<Channel> carry);

    void createChannel(Channel channel, Carry<Channel> carry);

    void deleteChannel(String id, Carry<Success> carry);
}