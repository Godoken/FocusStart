package com.example.myapplication.features.settings.data;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

public interface SettingsDataSource {

    void getSettingsPreferences(Carry<String> settings);
    void setSettingsPreferences(String settings, int index, Carry<Success> carry);
    void setStyleSettingsPreferences(String style, int index, Carry<Success> carry);
    void getStyleSettingsPreferences(Carry<String> carry);
}
