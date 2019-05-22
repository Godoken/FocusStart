package com.example.myapplication.features.settings.domain;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;

public interface SettingsRepository {

    void getPeriodSettings(Carry<Integer> carry);

    void setPeriodSettings(int period, int index, Carry<Success> carry);

    void setStyleSettings(String style, int index, Carry<Success> carry);

    void getStyleSettings(Carry<Integer> carry);
}
