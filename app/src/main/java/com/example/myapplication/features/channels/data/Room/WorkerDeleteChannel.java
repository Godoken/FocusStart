package com.example.myapplication.features.channels.data.Room;

import android.content.Context;

import com.example.myapplication.App;
import com.example.myapplication.features.channels.domain.model.Channel;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerDeleteChannel extends Worker {

    Data data;

    String url;
    String name;

    Channel channel;


    public WorkerDeleteChannel(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        data = getInputData();

        url = data.getString("url");
        name = data.getString("name");

        channel = new Channel(name, "", url);

        App.getDataBase().getChannelDao().delete(channel);

        return Result.success();

    }
}
