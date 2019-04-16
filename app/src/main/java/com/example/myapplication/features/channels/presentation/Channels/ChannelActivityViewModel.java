package com.example.myapplication.features.channels.presentation.Channels;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.channels.domain.ChannelsInteractor;
import com.example.myapplication.features.channels.domain.ChannelsInteractorImpl;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;


public class ChannelActivityViewModel extends BaseViewModel<ChannelListView> {


    private final ChannelsInteractor interactor;

    ChannelActivityViewModel(ChannelsInteractor interactor){
        this.interactor = interactor;
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

    public void onCreateChannelActivityClicked() {

        view.showProgress();
        view.createChannel();
        view.hideProgress();
        /*interactor.createChannel(channel, new Carry<Channel>() {
            @Override
            public void onSuccess(Channel result) {
                // Do toast
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Do toast
                view.hideProgress();
            }
        });*/
    }

    public void onCreateChannelClicked(Channel channel) {

        //view.showProgress();
        interactor.createChannel(channel, new Carry<Channel>() {
            @Override
            public void onSuccess(Channel result) {
                // Do toast
                //view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                // Do toast
                //view.hideProgress();
            }
        });
    }

    public void onChannelSelected(Channel channel) {

        view.showProgress();
        interactor.showNews(channel.getUrl(), new Carry<String>() {

            @Override
            public void onSuccess(String result_news) {
                view.hideProgress();

                //Test
                view.showNews(result_news);
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

        view.showProgress();
        view.deleteChannel(channel);
        view.hideProgress();
    }

    public void onDeleteChannelClicked(Channel channel){

        interactor.deleteChannel(channel, new Carry<Success>() {
            @Override
            public void onSuccess(Success result) {
                //
            }

            @Override
            public void onFailure(Throwable throwable) {
                //
            }
        });
    }

}
