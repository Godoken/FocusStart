package com.example.myapplication.features.settings.domain;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

public interface SettingsInteractor {

    void getAppSettings(Carry<String> carry);

    void setAppSettings(String settings, int index, Carry<Success> carry);

    void setStyleAppSettings(String style, int index, Carry<Success> carry);

    void getStyleAppSettings(Carry<String> carry);
}
