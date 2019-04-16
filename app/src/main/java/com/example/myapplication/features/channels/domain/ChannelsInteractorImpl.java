package com.example.myapplication.features.channels.domain;

import com.example.myapplication.exceptions.EmptyBodyException;
import com.example.myapplication.features.channels.data.ChannelsRepositoryImpl;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;

public class ChannelsInteractorImpl implements ChannelsInteractor {

    private final ChannelsRepository repository;

    private String channel_news;

    public ChannelsInteractorImpl(ChannelsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void loadChannels(Carry<List<Channel>> carry) {

        repository.loadChannels(carry);
    }

    @Override
    public void showNews(String url, Carry<String> carry) {

        repository.loadChannel(url, new Carry<Channel>() {
            @Override
            public void onSuccess(Channel result) {
                channel_news = result.getNews();
                carry.onSuccess(channel_news);
            }

            @Override
            public void onFailure(Throwable throwable) {
                carry.onFailure(new EmptyBodyException());
            }
        });

    }

    @Override
    public void createChannel(Channel channel, Carry<Channel> carry) {

        repository.createChannel(channel, carry);

    }

    @Override
    public void deleteChannel(Channel channel, Carry<Success> carry) {

        repository.deleteChannel(channel, carry);
    }
}
