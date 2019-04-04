package com.example.myapplication.features.channels.data;

import com.example.myapplication.App;
import com.example.myapplication.background.DatabaseWorker;
import com.example.myapplication.exceptions.EmptyBodyException;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.List;

import javax.xml.transform.Result;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleService;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class ChannelDataSourceIImpl implements ChannelsDataSource, LifecycleOwner  {

    //private LifecycleOwner lifecycleOwner;
    private Data data;
    @Override
    public void getChannels(Carry<List<Channel>> carry) {

        //List<Channel> channels = App.getDataBase().getChannelDao().getAllChannels();

        final List<Channel> channels;
        //Data data;

        //lifecycleOwner = new LifecycleService();

        OneTimeWorkRequest getChannelsWorkRequest = new OneTimeWorkRequest.Builder(DatabaseWorker.class)
                .build();

        WorkManager.getInstance().enqueue(getChannelsWorkRequest);


        WorkManager.getInstance().getWorkInfoByIdLiveData(getChannelsWorkRequest.getId()).observe(this
        , new Observer<WorkInfo>() {
            @Override
            public void onChanged(WorkInfo workInfo) {
                data = workInfo.getOutputData();
            }
        });


        /*if (channels != null){
            carry.onSuccess(channels);
        } else {
            carry.onFailure(new EmptyBodyException());
        }*/
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

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return new Lifecycle() {
            @Override
            public void addObserver(@NonNull LifecycleObserver observer) {

            }

            @Override
            public void removeObserver(@NonNull LifecycleObserver observer) {

            }

            @NonNull
            @Override
            public State getCurrentState() {
                return State.CREATED;
            }
        };
    }
}
