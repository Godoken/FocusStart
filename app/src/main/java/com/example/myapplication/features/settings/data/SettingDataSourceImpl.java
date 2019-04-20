package com.example.myapplication.features.settings.data;

import android.content.SharedPreferences;

import com.example.myapplication.exceptions.EmptyBodyException;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

public class SettingDataSourceImpl implements SettingsDataSource {

    private SharedPreferences sharedPreferences;
    private String settings_key;

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
                carry.onSuccess(new Success());

            } else {
                sharedPreferences.edit().putString(settings_key, settings).apply();
                carry.onSuccess(new Success());
            }

        } else {
            carry.onFailure(new EmptyBodyException());
        }
    }
}
