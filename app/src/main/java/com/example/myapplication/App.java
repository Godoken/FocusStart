package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.myapplication.features.channels.data.network.WorkerNet;
import com.example.myapplication.features.channels.data.room.AppDataBase;

import java.util.concurrent.TimeUnit;

import androidx.room.Room;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class App extends Application {

    private static AppDataBase dataBase;
    private static Context context;
    private PeriodicWorkRequest periodicWorkRequest;

    private SharedPreferences sharedPreferences;

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
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);

        if ((!sharedPreferences.getString("SETTINGS_KEY", "1000").equals("0")) & (!sharedPreferences.getString("SETTINGS_KEY", "1000").equals(""))){
            periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkerNet.class, Integer.valueOf(sharedPreferences.getString("SETTINGS_KEY", "30")), TimeUnit.MINUTES)
                    .addTag("TAG_PERIODIC_WORKER_NET")
                    .build();
            WorkManager.getInstance().enqueue(periodicWorkRequest);
        } else {
            WorkManager.getInstance().cancelAllWorkByTag("TAG_PERIODIC_WORKER_NET");
        }
    }
}
