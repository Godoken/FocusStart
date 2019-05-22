package com.example.myapplication.features.settings.domain;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.support.Carry;

public class SettingsInteractorImpl implements SettingsInteractor {

    private SettingsRepository settingsRepository;

    public SettingsInteractorImpl(SettingsRepository settingsRepository){
        this.settingsRepository = settingsRepository;
    }

    @Override
    public void getPeriodAppSettings(Carry<Integer> carry) {
        settingsRepository.getPeriodSettings(carry);
    }

    @Override
    public void setPeriodAppSettings(int period, int index, Carry<Success> carry) {
        settingsRepository.setPeriodSettings(period, index, carry);
    }

    @Override
    public void setStyleAppSettings(String style, int index, Carry<Success> carry) {
        settingsRepository.setStyleSettings(style, index, carry);
    }

    @Override
    public void getStyleAppSettings(Carry<Integer> carry) {
        settingsRepository.getStyleSettings(carry);
    }
}
