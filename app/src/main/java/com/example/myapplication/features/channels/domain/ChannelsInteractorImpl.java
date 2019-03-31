package com.example.myapplication.features.channels.domain;

import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;

public final class ChannelsInteractorImpl implements ChannelsInteractor {

    private final ChannelsRepository repository;

    public ChannelsInteractorImpl(ChannelsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadChannels(Carry<List<Channel>> carry) {
        repository.loadChannels(carry);
    }

    @Override
    public void loadChannel(String id, Carry<Channel> carry) {
        repository.loadChannel(id, carry);
    }

    @Override
    public void createChannel(Channel channel, Carry<Channel> carry) {
        repository.createChannel(channel, carry);
    }

    @Override
    public void deleteChannel(String id, Carry<Success> carry) {
        repository.deleteChannel(id, carry);
    }
}
