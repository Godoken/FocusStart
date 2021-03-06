package com.example.myapplication.features.channels.data;

import com.example.myapplication.features.channels.domain.ChannelsRepository;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;

import java.util.List;

public class ChannelsRepositoryImpl implements ChannelsRepository {

    private final ChannelsDataSource channelsDataSource;

    public ChannelsRepositoryImpl(ChannelsDataSource channelsDataSource){
        this.channelsDataSource = channelsDataSource;
    }
    @Override
    public void loadChannels(Carry<List<Channel>> carry) {

        channelsDataSource.getChannels(carry);

    }

    @Override
    public void loadChannel(String url, Carry<Channel> carry) {

        channelsDataSource.getChannel(url, carry);

    }

    @Override
    public void createChannel(Channel channel, Carry<Channel> carry) {

        channelsDataSource.createChannel(channel, carry);

    }

    @Override
    public void deleteChannel(Channel channel, Carry<Success> carry) {

        channelsDataSource.deleteChannel(channel, carry);

    }
}
