package com.example.myapplication.features.settings.data;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;

public interface SettingsDataSource {

    void getPeriodSettingsPreferences(Carry<Integer> settings);
    void setPeriodSettingsPreferences(int period, int index, Carry<Success> carry);
    void setStyleSettingsPreferences(String style, int index, Carry<Success> carry);
    void getStyleSettingsPreferences(Carry<Integer> carry);
}
