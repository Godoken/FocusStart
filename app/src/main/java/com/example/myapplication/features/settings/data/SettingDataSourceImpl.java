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

    private String style_settings_key;

    private PeriodicWorkRequest periodicWorkRequest;
    private String periodic_tag = "TAG_PERIODIC_WORKER_NET";

    public SettingDataSourceImpl(SharedPreferences sharedPreferences){

        this.sharedPreferences  = sharedPreferences;
        this.settings_key = "SETTINGS_KEY";
        this.style_settings_key = "STYLE_SETTINGS_KEY";
    }

    @Override
    public void getSettingsPreferences(Carry<String> settings) {

        int savedRadioIndex = sharedPreferences.getInt(
                "style", -1);

        if (savedRadioIndex != -1){
            settings.onSuccess(String.valueOf(savedRadioIndex));
        } else {
            settings.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void setSettingsPreferences(String settings, int index, Carry<Success> carry) {

        if (sharedPreferences != null){

            if (sharedPreferences.contains(settings_key)){

                sharedPreferences.edit().remove(settings_key).apply();
                sharedPreferences.edit().putString(settings_key, settings).apply();

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt("period", index);
                edit.apply();

                startPeriodicWorker();
                carry.onSuccess(new Success());

            } else {
                sharedPreferences.edit().putString(settings_key, settings).apply();

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt("period", index);
                edit.apply();

                startPeriodicWorker();
                carry.onSuccess(new Success());
            }

        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void setStyleSettingsPreferences(String style, int index, Carry<Success> carry) {

        if (sharedPreferences != null){

            if (sharedPreferences.contains(style_settings_key)){

                sharedPreferences.edit().remove(style_settings_key).apply();
                sharedPreferences.edit().putString(style_settings_key, style).apply();

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt("style", index);
                edit.apply();

                carry.onSuccess(new Success());

            } else {
                sharedPreferences.edit().putString(style_settings_key, style).apply();

                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putInt("style", index);
                edit.apply();

                carry.onSuccess(new Success());
            }

        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void getStyleSettingsPreferences(Carry<String> carry) {

        int savedRadioIndex = sharedPreferences.getInt(
                "period", -1);

        if (savedRadioIndex != -1){
            carry.onSuccess(String.valueOf(savedRadioIndex));
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
