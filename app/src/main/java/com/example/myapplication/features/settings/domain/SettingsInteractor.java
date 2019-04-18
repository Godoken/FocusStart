package com.example.myapplication.features.settings.domain;

import com.example.myapplication.network.Carry;

import java.util.ArrayList;

public interface SettingsInteractor {

    void getAppSettings(Carry<ArrayList<String>> carry);

    void setAppSettings(Carry<ArrayList<String>> carry);
}
