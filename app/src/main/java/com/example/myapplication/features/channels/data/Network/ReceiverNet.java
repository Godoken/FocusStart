package com.example.myapplication.features.channels.data.Network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.myapplication.App;

import java.util.concurrent.TimeUnit;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class ReceiverNet extends BroadcastReceiver {

    public ReceiverNet(){
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        PeriodicWorkRequest periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkerNet.class, 30, TimeUnit.MINUTES,
                25, TimeUnit.MINUTES).build();

        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }
}
