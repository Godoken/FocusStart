package com.example.myapplication.features.channels.presentation;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.channels.domain.ChannelsInteractor;
import com.example.myapplication.features.channels.domain.ChannelsInteractorImpl;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.network.Carry;

import java.util.List;


public class ChannelActivityViewModel extends BaseViewModel<ChannelListView> {


    private final ChannelsInteractor interactor;

    ChannelActivityViewModel(){
        interactor = new ChannelsInteractorImpl();
    }

    @Override
    protected void onViewReady() {
        loadChannels();
    }

    public void loadChannels(){
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

    public void onCreateChannelClicked() {
    }

    public void onChannelSelected(Channel channel) {

        view.showProgress();
        interactor.showNews(channel.getUrl(), new Carry<Channel>() {

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

    public void onChannelLongClicked(Channel channel) {
    }

}
