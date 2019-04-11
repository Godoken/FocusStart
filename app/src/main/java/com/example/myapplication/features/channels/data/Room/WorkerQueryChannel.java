package com.example.myapplication.features.channels.data.Room;

import android.content.Context;

import com.example.myapplication.App;
import com.example.myapplication.features.channels.domain.model.Channel;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class WorkerQueryChannel extends Worker {

    Data data;

    String url;
    String name;
    String news;

    Channel channel;


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
