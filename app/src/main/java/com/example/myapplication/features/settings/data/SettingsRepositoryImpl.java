package com.example.myapplication.features.settings.data;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.features.settings.domain.SettingsRepository;
import com.example.myapplication.support.Carry;

public class SettingsRepositoryImpl implements SettingsRepository {

    private SettingsDataSource settingsDataSource;

    public SettingsRepositoryImpl(SettingsDataSource settingsDataSource){
        this.settingsDataSource = settingsDataSource;
    }
    @Override
    public void getPeriodSettings(Carry<Integer> carry) {

        settingsDataSource.getPeriodSettingsPreferences(carry);

    }

    @Override
    public void setPeriodSettings(int period, int index, Carry<Success> carry) {

        settingsDataSource.setPeriodSettingsPreferences(period, index, carry);

    }

    @Override
    public void setStyleSettings(String style, int index, Carry<Success> carry) {

        settingsDataSource.setStyleSettingsPreferences(style, index, carry);
    }

    @Override
    public void getStyleSettings(Carry<Integer> carry) {

        settingsDataSource.getStyleSettingsPreferences(carry);
    }
}
