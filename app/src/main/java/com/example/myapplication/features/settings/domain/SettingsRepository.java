package com.example.myapplication.features.settings.domain;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

public interface SettingsRepository {

    void getSettings(Carry<String> carry);

    void setSettings(String settings, Carry<Success> carry);
}
