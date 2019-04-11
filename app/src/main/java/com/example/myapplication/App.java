package com.example.myapplication;

import android.app.Application;
import android.content.Context;

import com.example.myapplication.features.channels.data.Room.AppDataBase;

import androidx.room.Room;

public class App extends Application {

    private static AppDataBase dataBase;
    private static Context context;


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
        context = getApplicationContext(); }

}
