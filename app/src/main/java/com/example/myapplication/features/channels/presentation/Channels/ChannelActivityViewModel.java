package com.example.myapplication.features.channels.presentation.Channels;

import android.content.Intent;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.channels.domain.ChannelsInteractor;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;


public class ChannelActivityViewModel extends BaseViewModel<ChannelListView> {


    private final ChannelsInteractor interactor;
    private String action;
    private String url;

    ChannelActivityViewModel(ChannelsInteractor interactor){
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        checkDeepLink(new Carry<Success>() {
            @Override
            public void onSuccess(Success result) {
                loadChannels();
            }

            @Override
            public void onFailure(Throwable throwable) {

            }
        });
    }

    private void checkDeepLink(Carry<Success> carry){
         if (Intent.ACTION_VIEW.equals(action) && url != null){
             Channel channel = new Channel(url.substring(url.lastIndexOf("/")), "", url);
             action = null;
             url = null;
             interactor.createChannel(channel, new Carry<Channel>() {
                 @Override
                 public void onSuccess(Channel result) {
                     //
                     carry.onSuccess(new Success());
                 }

                 @Override
                 public void onFailure(Throwable throwable) {
                    //
                 }
             });
         } else {
             carry.onSuccess(new Success());
         }
     }

    private void loadChannels(){

        view.showProgress();
        interactor.loadChannels(new Carry<List<Channel>>() {

            @Override
            public void onSuccess(List<Channel> result) {

                if (view != null){

                    view.showChannelList(result);
                    view.hideProgress();
                }
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
    }

    public void onCreateChannelClicked(Channel channel) {

        interactor.createChannel(channel, new Carry<Channel>() {
            @Override
            public void onSuccess(Channel result) {
            }

            @Override
            public void onFailure(Throwable throwable) {
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

    public void onSettingsAppActivityClicked() {
        view.showProgress();
        view.startAppSettings();
        view.hideProgress();

    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void onBackPressed() {
        view.showProgress();
        view.openQuitDialog();
        view.hideProgress();
    }
}
