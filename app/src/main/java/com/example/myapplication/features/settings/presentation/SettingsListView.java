package com.example.myapplication.features.settings.presentation;

import com.example.myapplication.features.InterfaceView;

import java.util.ArrayList;

public interface SettingsListView extends InterfaceView {

    void showProgress();

    void hideProgress();

    void showError(String message);

    void showSettings(String setting);
}
