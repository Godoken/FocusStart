package com.example.myapplication.features.channels.presentation.Channels;

import com.example.myapplication.features.channels.domain.ChannelsInteractor;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;

public class FragmentViewModel {

    private final ChannelsInteractor interactor;

    FragmentViewModel(ChannelsInteractor interactor){
        this.interactor  = interactor;
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
