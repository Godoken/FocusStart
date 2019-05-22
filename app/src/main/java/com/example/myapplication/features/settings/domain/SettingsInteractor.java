package com.example.myapplication.features.settings.domain;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;

public interface SettingsInteractor {

    void getPeriodAppSettings(Carry<Integer> carry);

    void setPeriodAppSettings(int period, int index, Carry<Success> carry);

    void setStyleAppSettings(String style, int index, Carry<Success> carry);

    void getStyleAppSettings(Carry<Integer> carry);
}
