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
    public void setSettings(String settings, int index, Carry<Success> carry) {

        settingsDataSource.setSettingsPreferences(settings, index, carry);

    }

    @Override
    public void setStyleSettings(String style, int index, Carry<Success> carry) {

        settingsDataSource.setStyleSettingsPreferences(style, index, carry);
    }

    @Override
    public void getStyleSettings(Carry<String> carry) {

        settingsDataSource.getStyleSettingsPreferences(carry);
    }
}
