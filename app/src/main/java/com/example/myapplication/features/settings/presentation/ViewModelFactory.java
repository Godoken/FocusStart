package com.example.myapplication.features.settings.presentation;

import com.example.myapplication.features.settings.domain.SettingsInteractor;
import com.example.myapplication.features.settings.domain.SettingsInteractorImpl;

public class ViewModelFactory {

    static SettingsActivityViewModel createSettingsActivityViewModel(){

        final SettingsInteractor settingsInteractor = new SettingsInteractorImpl();

        return new SettingsActivityViewModel(settingsInteractor);
    }
}
