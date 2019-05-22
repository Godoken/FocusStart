package com.example.myapplication.features.settings.presentation;

import com.example.myapplication.App;
import com.example.myapplication.features.settings.data.SettingDataSourceImpl;
import com.example.myapplication.features.settings.data.SettingsDataSource;
import com.example.myapplication.features.settings.data.SettingsRepositoryImpl;
import com.example.myapplication.features.settings.domain.SettingsInteractor;
import com.example.myapplication.features.settings.domain.SettingsInteractorImpl;
import com.example.myapplication.features.settings.domain.SettingsRepository;
import com.example.myapplication.support.SupporterSharedPreferences;

public class ViewModelFactory {

    static SettingsActivityViewModel createSettingsActivityViewModel(){

        final SupporterSharedPreferences supporterSharedPreferences = App.getSupporterSharedPreferences();

        final SettingsDataSource settingsDataSource = new SettingDataSourceImpl(supporterSharedPreferences);

        final SettingsRepository settingsRepository = new SettingsRepositoryImpl(settingsDataSource);

        final SettingsInteractor settingsInteractor = new SettingsInteractorImpl(settingsRepository);

        return new SettingsActivityViewModel(settingsInteractor);
    }
}
