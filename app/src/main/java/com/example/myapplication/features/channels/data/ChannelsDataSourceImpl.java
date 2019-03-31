package com.example.myapplication.features.channels.data;

import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;
import com.example.myapplication.network.DefaultCallback;

import java.util.List;

public final class ChannelsDataSourceImpl implements ChannelsDataSource {

    private final ChannelsApi channelsApi;

    public ChannelsDataSourceImpl(ChannelsApi channelsApi) {
        this.channelsApi = channelsApi;
    }

    @Override
    public void getChannels(final Carry<List<Channel>> carry) {
        channelsApi.getChannelList().enqueue(new DefaultCallback(carry));
    }

    @Override
    public void getChannel(String id, Carry<Channel> carry) {
        channelsApi.getChannel(id).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void createChannel(Channel channel, Carry<Channel> carry) {
        channelsApi.createChannel(channel).enqueue(new DefaultCallback(carry));
    }

    @Override
    public void deleteChannel(String id, Carry<Success> carry) {
        channelsApi.deleteChannel(id).enqueue(new DefaultCallback(carry));
    }
}
