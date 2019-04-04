package com.example.myapplication.features.channels.domain;

import com.example.myapplication.features.channels.data.ChannelsRepositoryImpl;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;

public class ChannelsInteractorImpl implements ChannelsInteractor {

    private final ChannelsRepository repository;

    public ChannelsInteractorImpl() {
        repository = new ChannelsRepositoryImpl();
    }

    @Override
    public void loadChannels(Carry<List<Channel>> carry) {

        repository.loadChannels(carry);
    }

    @Override
    public void showNews(String url, Carry<Channel> carry) {

    }

    @Override
    public void createChannel(Channel channel, Carry<Channel> carry) {

    }

    @Override
    public void deleteChannel(String url, Carry<Success> carry) {

    }
}
