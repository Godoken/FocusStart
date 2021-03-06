package com.example.myapplication.features.channels.domain;

import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;

import java.util.List;

public interface ChannelsRepository {

    void loadChannels(Carry<List<Channel>> carry);

    void loadChannel(String id, Carry<Channel> carry);

    void createChannel(Channel channel, Carry<Channel> carry);

    void deleteChannel(Channel channel, Carry<Success> carry);
}
