package com.example.myapplication;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

import androidx.room.Room;

import com.example.myapplication.features.channels.data.room.AppDataBase;
import com.example.myapplication.support.SupporterSharedPreferences;
import com.example.myapplication.support.SupporterSharedPreferencesImpl;

public class App extends Application {

    private static AppDataBase dataBase;
    private static Context context;
    private static SupporterSharedPreferences supporterSharedPreferences;

    public static AppDataBase getDataBase() {
        return dataBase;
    }

    public static Context getAppContext(){
        return context;
    }

    public static SupporterSharedPreferences getSupporterSharedPreferences(){
        return supporterSharedPreferences;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dataBase = Room.databaseBuilder(getApplicationContext(), AppDataBase.class, "channels-database").build();
        context = getApplicationContext();
        supporterSharedPreferences = new SupporterSharedPreferencesImpl(PreferenceManager.getDefaultSharedPreferences(getAppContext()));
    }
}
