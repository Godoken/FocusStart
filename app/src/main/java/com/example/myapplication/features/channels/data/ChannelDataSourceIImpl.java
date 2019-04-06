package com.example.myapplication.features.channels.data;


import com.example.myapplication.App;
import com.example.myapplication.exceptions.EmptyBodyException;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;


public class ChannelDataSourceIImpl implements ChannelsDataSource  {

    private Data data;
    private List<Channel> channels = new ArrayList<>();
    private LiveData<List<Channel>> listLiveData;
    @Override
    public void getChannels(Carry<List<Channel>> carry) {

        listLiveData = App.getDataBase().getChannelDao().getAllChannels();

        listLiveData.observeForever(new Observer<List<Channel>>() {
            @Override
            public void onChanged(List<Channel> channelList) {

                if (listLiveData != null){
                    channels = listLiveData.getValue();
                    carry.onSuccess(channels);
                } else {
                    carry.onFailure(new EmptyBodyException());
                }

            }
        });
    }

    @Override
    public void getChannel(String id, Carry<Channel> carry) {

    }

    @Override
    public void createChannel(Channel channel, Carry<Channel> carry) {

    }

    @Override
    public void deleteChannel(String id, Carry<Success> carry) {

    }

}
