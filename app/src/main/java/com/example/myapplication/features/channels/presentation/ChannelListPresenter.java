package com.example.myapplication.features.channels.presentation;

import com.example.myapplication.features.MvpPresenter;
import com.example.myapplication.features.channels.domain.ChannelsInteractor;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

final class ChannelListPresenter extends MvpPresenter<ChannelListView> {

    private final ChannelsInteractor interactor;

    ChannelListPresenter(ChannelsInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        loadChannels();
    }

    private void loadChannels() {
        view.showProgress();
        interactor.loadChannels(new Carry<List<Channel>>() {

            @Override
            public void onSuccess(List<Channel> result) {
                view.showChannelList(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });
    }

    void onChannelSelected(Channel channel) {
        view.showProgress();
        interactor.loadChannel(channel.getId(), new Carry<Channel>() {

            @Override
            public void onSuccess(Channel result) {
                view.hideProgress();
                // do something
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });
    }

    void onChannelLongClicked(Channel channel) {
        view.showProgress();
        interactor.deleteChannel(channel.getId(), new Carry<Success>() {

            @Override
            public void onSuccess(Success result) {
                loadChannels();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    private final AtomicInteger atomicInteger = new AtomicInteger();

    public void onCreateChannelClicked() {
        int id = atomicInteger.incrementAndGet();
        String name = "Name_" + id;
        String author = "Author_" + id;
        int pages = 7 * id;

        Channel book = new Channel(name, author, String.valueOf(pages));
        interactor.createChannel(book, new Carry<Channel>() {
            @Override
            public void onSuccess(Channel result) {
                loadChannels();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
            }
        });
    }

}
