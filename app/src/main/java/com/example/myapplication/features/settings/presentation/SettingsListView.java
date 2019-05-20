package com.example.myapplication.features.settings.presentation;

import com.example.myapplication.features.InterfaceView;

public interface SettingsListView extends InterfaceView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void loadStylePreferences(int index);

    void loadPeriodicPreferences(int index);
}
