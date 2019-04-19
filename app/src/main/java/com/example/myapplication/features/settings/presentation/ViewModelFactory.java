package com.example.myapplication.features.settings.presentation;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.myapplication.App;
import com.example.myapplication.features.settings.data.SettingDataSourceImpl;
import com.example.myapplication.features.settings.data.SettingsDataSource;
import com.example.myapplication.features.settings.data.SettingsRepositoryImpl;
import com.example.myapplication.features.settings.domain.SettingsInteractor;
import com.example.myapplication.features.settings.domain.SettingsInteractorImpl;
import com.example.myapplication.features.settings.domain.SettingsRepository;

public class ViewModelFactory {

    static SettingsActivityViewModel createSettingsActivityViewModel(){

        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(App.getAppContext());

        final SettingsDataSource settingsDataSource = new SettingDataSourceImpl(sharedPreferences);

        final SettingsRepository settingsRepository = new SettingsRepositoryImpl(settingsDataSource);

        final SettingsInteractor settingsInteractor = new SettingsInteractorImpl(settingsRepository);

        return new SettingsActivityViewModel(settingsInteractor);
    }
}
