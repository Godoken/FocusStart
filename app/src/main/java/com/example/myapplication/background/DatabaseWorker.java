package com.example.myapplication.background;

import android.annotation.SuppressLint;
import android.content.Context;

import com.example.myapplication.App;
import com.example.myapplication.features.channels.domain.model.Channel;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import androidx.annotation.NonNull;
import androidx.collection.ArrayMap;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DatabaseWorker extends Worker {

    public DatabaseWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        List<Channel> channelList = App.getDataBase().getChannelDao().getAllChannels();
        //Channel[] channels = new Channel[channelList.size()];

        Map<String, Channel> channelMap = new LinkedHashMap<>();
        for (int i = 0; i < channelList.size(); i++){
            //channels[i] = channelList.get(i);
            channelMap.put(channelList.get(i).getUrl(), channelList.get(i));
        }

        //Map<>

        @SuppressLint("RestrictedApi") Data output = new Data.Builder().put("", channelList.toArray()).build();

        //Data output = new Data.Builder().put("", channelList).build();

        return Result.success(output);
    }

}
