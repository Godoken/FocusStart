package com.example.myapplication.features.channels.data;

import com.example.myapplication.features.channels.domain.ChannelsRepository;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;

public final class ChannelsRepositoryImpl implements ChannelsRepository {

    private final ChannelsDataSource dataSource;

    public ChannelsRepositoryImpl(ChannelsDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void loadChannels(Carry<List<Channel>> carry) {
        dataSource.getChannels(carry);
    }

    @Override
    public void loadChannel(String id, Carry<Channel> carry) {
        dataSource.getChannel(id, carry);
    }

    @Override
    public void createChannel(Channel book, Carry<Channel> carry) {
        dataSource.createChannel(book, carry);
    }

    @Override
    public void deleteChannel(String id, Carry<Success> carry) {
        dataSource.deleteChannel(id, carry);
    }
}
