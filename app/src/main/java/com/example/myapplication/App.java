package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.features.channels.data.Room.AppDataBase;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.room.Room;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

public class App extends Application {

    private static AppDataBase dataBase;
    private static Context context;


    public static AppDataBase getDataBase() {
        return dataBase;
    }

    public static Context getAppContext(){
        return context;
    }

    /*private static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "channels-database").build();
        context = getApplicationContext(); }

}
