package com.example.myapplication.features.settings.data;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.features.settings.domain.SettingsRepository;
import com.example.myapplication.network.Carry;

public class SettingsRepositoryImpl implements SettingsRepository {

    private SettingsDataSource settingsDataSource;

    public SettingsRepositoryImpl(SettingsDataSource settingsDataSource){
        this.settingsDataSource = settingsDataSource;
    }
    @Override
    public void getSettings(Carry<String> carry) {

        settingsDataSource.getSettingsPreferences(carry);

    }

    @Override
    public void setSettings(String settings, Carry<Success> carry) {

        settingsDataSource.setSettingsPreferences(settings, carry);

    }
}
