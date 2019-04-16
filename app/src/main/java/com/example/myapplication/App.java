package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.myapplication.features.channels.data.Network.WorkerNet;
import com.example.myapplication.features.channels.data.Room.AppDataBase;

import java.util.concurrent.TimeUnit;

import androidx.room.Room;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class App extends Application {

    private static AppDataBase dataBase;
    private static Context context;
    private PeriodicWorkRequest periodicWorkRequest;


    public static AppDataBase getDataBase() {
        return dataBase;
    }

    public static Context getAppContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "channels-database").build();
        context = getApplicationContext();

        periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkerNet.class, 30, TimeUnit.MINUTES)
                .addTag("TAG_PERIODIC_WORKER_NET")
                .build();
        WorkManager.getInstance().enqueue(periodicWorkRequest);
    }
}
