package com.example.myapplication.features.settings.presentation;

import com.example.myapplication.features.InterfaceView;

public interface SettingsListView extends InterfaceView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void showSettings(String setting);

    void showStyleSettings(String setting);

    //void setAppStyle(String style);
}
