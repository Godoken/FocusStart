package com.example.myapplication.features.channels.data.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.example.myapplication.App;
import com.example.myapplication.features.channels.domain.model.Channel;

public class WorkerQueryChannel extends Worker {

    private Data data;
    private String url;
    private String name;
    private String news;
    private Channel channel;

    public WorkerQueryChannel(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        data = getInputData();
        url = data.getString("url");
        channel = App.getDataBase().getChannelDao().getChannelByUrl(url);
        name = channel.getName();
        news = channel.getNews();
        data =  new Data.Builder().putString("url", url).putString("name", name).putString("news", news).build();

        return Result.success(data);
    }
}
