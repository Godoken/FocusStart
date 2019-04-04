package com.example.myapplication.features.channels.data;

import com.example.myapplication.features.channels.domain.ChannelsRepository;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;

public class ChannelsRepositoryImpl implements ChannelsRepository {

    private final ChannelsDataSource channelsDataSource;

    public ChannelsRepositoryImpl(){
        channelsDataSource = new ChannelDataSourceIImpl();
    }
    @Override
    public void loadChannels(Carry<List<Channel>> carry) {

        channelsDataSource.getChannels(carry);

    }

    @Override
    public void loadChannel(String id, Carry<Channel> carry) {

    }

    @Override
    public void createChannel(Channel channel, Carry<Channel> carry) {

    }

    @Override
    public void deleteChannel(String id, Carry<Success> carry) {

    }
}
