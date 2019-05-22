package com.example.myapplication.features.settings.data;

import android.content.SharedPreferences;

import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.example.myapplication.exceptions.EmptyBodyException;
import com.example.myapplication.features.channels.data.network.WorkerNet;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;
import com.example.myapplication.support.SupporterSharedPreferences;

import java.util.concurrent.TimeUnit;

public class SettingDataSourceImpl implements SettingsDataSource {

    private SharedPreferences sharedPreferences;

    private String periodKey;
    private String styleKey;
    private String periodicTag;

    private String periodRadioGroupKey;
    private String styleRadioGroupKey;

    private PeriodicWorkRequest periodicWorkRequest;


    public SettingDataSourceImpl(SupporterSharedPreferences supporterSharedPreferences){

        this.sharedPreferences = supporterSharedPreferences.getSharedPreferences();

        this.periodicTag = supporterSharedPreferences.getPeriodicTag();
        this.periodKey = supporterSharedPreferences.getPeriodKey();
        this.styleKey = supporterSharedPreferences.getStyleKey();

        this.periodRadioGroupKey = supporterSharedPreferences.getPeriodRadioGroupKey();
        this.styleRadioGroupKey = supporterSharedPreferences.getStyleRadioGroupKey();
    }

    @Override
    public void getPeriodSettingsPreferences(Carry<Integer> settings) {

        int savedRadioIndex = sharedPreferences.getInt(
                periodRadioGroupKey, -1);

        if (savedRadioIndex != -1){
            settings.onSuccess(savedRadioIndex);
        } else {
            settings.onFailure(new EmptyBodyException());
        }
    }

    @Override
    public void setPeriodSettingsPreferences(int period, int index, Carry<Success> carry) {

        sharedPreferences.edit().putInt(periodKey, period).apply();

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(periodRadioGroupKey, index);
        edit.apply();

        startPeriodicWorker();
        carry.onSuccess(new Success());

    }

    @Override
    public void setStyleSettingsPreferences(String style, int index, Carry<Success> carry) {

        sharedPreferences.edit().putString(styleKey, style).apply();

        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(styleRadioGroupKey, index);
        edit.apply();

        carry.onSuccess(new Success());

    }

    @Override
    public void getStyleSettingsPreferences(Carry<Integer> carry) {

        int savedRadioIndex = sharedPreferences.getInt(
                styleRadioGroupKey, -1);

        if (savedRadioIndex != -1){
            carry.onSuccess(savedRadioIndex);
        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }

    private void startPeriodicWorker(){

        if ((sharedPreferences.getInt(periodKey, 30) != 0)){
            periodicWorkRequest = new PeriodicWorkRequest.Builder(WorkerNet.class, sharedPreferences.getInt(periodKey, 30), TimeUnit.MINUTES)
                    .addTag(periodicTag)
                    .build();
            WorkManager.getInstance().enqueue(periodicWorkRequest);
        } else {
            WorkManager.getInstance().cancelAllWorkByTag(periodicTag);
        }

    }
}
