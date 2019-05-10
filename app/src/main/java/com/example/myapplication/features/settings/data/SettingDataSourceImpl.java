package com.example.myapplication.features.settings.data;

import android.content.SharedPreferences;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.myapplication.exceptions.EmptyBodyException;
import com.example.myapplication.features.channels.data.network.WorkerNet;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.concurrent.TimeUnit;

public class SettingDataSourceImpl implements SettingsDataSource {

    private SharedPreferences sharedPreferences;
    private String settings_key;

    private PeriodicWorkRequest periodicWorkRequest;
    private String periodic_tag = "TAG_PERIODIC_WORKER_NET";

    public SettingDataSourceImpl(SharedPreferences sharedPreferences){
        this.sharedPreferences  = sharedPreferences;
        this.settings_key = "SETTINGS_KEY";
    }

    @Override
    public void getSettingsPreferences(Carry<String> settings) {

        if (sharedPreferences.contains(settings_key)){
            settings.onSuccess(sharedPreferences.getString(settings_key,  "0"));
        } else {
            settings.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void setSettingsPreferences(String settings, Carry<Success> carry) {

        if (sharedPreferences != null){

            if (sharedPreferences.contains(settings_key)){

                sharedPreferences.edit().remove(settings_key).apply();
                sharedPreferences.edit().putString(settings_key, settings).apply();
                startPeriodicWorker();
                carry.onSuccess(new Success());

            } else {
                sharedPreferences.edit().putString(settings_key, settings).apply();
                startPeriodicWorker();
                carry.onSuccess(new Success());
            }

        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }

    private void startPeriodicWorker(){

        if ((!sharedPreferences.getString(settings_key, "1000").equals("0")) & (!sharedPreferences.getString(settings_key, "1000").equals(""))){
            periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkerNet.class, Integer.valueOf(sharedPreferences.getString(settings_key, "30")), TimeUnit.MINUTES)
                    .addTag(periodic_tag)
                    .build();
            WorkManager.getInstance().enqueue(periodicWorkRequest);
        } else {
            WorkManager.getInstance().cancelAllWorkByTag(periodic_tag);
        }

    }
}
