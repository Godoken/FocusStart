package com.example.myapplication.features.settings.domain;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

public class SettingsInteractorImpl implements SettingsInteractor {

    private SettingsRepository settingsRepository;

    public SettingsInteractorImpl(SettingsRepository settingsRepository){
        this.settingsRepository = settingsRepository;
    }

    @Override
    public void getAppSettings(Carry<String> carry) {

        settingsRepository.getSettings(carry);

    }

    @Override
    public void setAppSettings(String settings, Carry<Success> carry) {

        settingsRepository.setSettings(settings, carry);

    }
}
