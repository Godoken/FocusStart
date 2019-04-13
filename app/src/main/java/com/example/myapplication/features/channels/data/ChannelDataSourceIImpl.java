package com.example.myapplication.features.channels.data;


import com.example.myapplication.App;
import com.example.myapplication.exceptions.EmptyBodyException;
import com.example.myapplication.features.channels.data.Network.WorkerNet;
import com.example.myapplication.features.channels.data.Room.WorkerDeleteChannel;
import com.example.myapplication.features.channels.data.Room.WorkerInsertChannel;
import com.example.myapplication.features.channels.data.Room.WorkerQueryChannel;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;


public class ChannelDataSourceIImpl implements ChannelsDataSource  {

    private List<Channel> channels = new ArrayList<>();
    private LiveData<List<Channel>> listLiveData;

    private LiveData<Channel> channelLiveData;

    Data data;

    @Override
    public void getChannels(Carry<List<Channel>> carry) {

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkerNet.class)
                //.setInitialDelay(10, TimeUnit.SECONDS)
                .build();

        WorkManager.getInstance().enqueue(oneTimeWorkRequest);

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
    public void getChannel(String url, Carry<Channel> carry) {

        data = new Data.Builder().putString("url", url).build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest
                .Builder(WorkerQueryChannel.class)
                .setInputData(data)
                .build();



        WorkManager.getInstance().enqueue(oneTimeWorkRequest);

        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observeForever(new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {

                String url_output;
                String name_output;
                String news_output;

                Channel channel;

                if (workInfo.getState() == WorkInfo.State.SUCCEEDED){

                    data = workInfo.getOutputData();

                    url_output = data.getString("url");
                    name_output = data.getString("name");
                    news_output = data.getString("news");

                    channel = new Channel(name_output, news_output, url_output);


                    carry.onSuccess(channel);
                } else {

                    if (workInfo.getState() == WorkInfo.State.FAILED){
                        carry.onFailure(new EmptyBodyException());
                    }
                    //carry.onFailure(new EmptyBodyException());
                }
            }
        });

        /*channelLiveData = App.getDataBase().getChannelDao().getChannelByUrl(url);

        Observer<Channel> observer = new Observer<Channel>() {
            @Override
            public void onChanged(Channel channel) {
                if (channelLiveData != null){
                    channel = channelLiveData.getValue();
                    carry.onSuccess(channel);
                } else {
                    carry.onFailure(new EmptyBodyException());
                }
            }
        };
        channelLiveData.observeForever(observer);
        //channelLiveData.removeObserver(observer);*/
    }

    @Override
    public void createChannel(Channel channel, Carry<Channel> carry) {

        //App.getDataBase().getChannelDao().insert(channel);

        data = new Data.Builder().putString("url", channel.getUrl()).putString("name", channel.getName()).build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkerInsertChannel.class).setInputData(data).build();

        WorkManager.getInstance().enqueue(oneTimeWorkRequest);

        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observeForever(new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {

                if (workInfo.getState() == WorkInfo.State.SUCCEEDED){

                    OneTimeWorkRequest oneTimeWorkRequest1 = new OneTimeWorkRequest.Builder(WorkerNet.class).build();
                    WorkManager.getInstance().enqueue(oneTimeWorkRequest1);

                    carry.onSuccess(channel);
                } else {
                    carry.onFailure(new EmptyBodyException());
                }
            }
        });
    }

    @Override
    public void deleteChannel(Channel channel, Carry<Success> carry) {

        data = new Data.Builder().putString("url", channel.getUrl()).putString("name", channel.getName()).build();

        OneTimeWorkRequest oneTimeWorkRequest = new OneTimeWorkRequest.Builder(WorkerDeleteChannel.class).setInputData(data).build();

        WorkManager.getInstance().enqueue(oneTimeWorkRequest);

        WorkManager.getInstance().getWorkInfoByIdLiveData(oneTimeWorkRequest.getId()).observeForever(new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {

                if (workInfo.getState() == WorkInfo.State.SUCCEEDED){

                    Success success = new Success();
                    carry.onSuccess(success);
                } else {
                    carry.onFailure(new EmptyBodyException());
                }
            }
        });
    }
}
