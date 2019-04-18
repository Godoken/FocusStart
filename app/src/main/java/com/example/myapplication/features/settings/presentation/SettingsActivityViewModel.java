package com.example.myapplication.features.settings.presentation;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.settings.domain.SettingsInteractor;
import com.example.myapplication.network.Carry;

import java.util.ArrayList;

public class SettingsActivityViewModel extends BaseViewModel<SettingsListView> {

    private SettingsInteractor settingsInteractor;


    SettingsActivityViewModel(SettingsInteractor settingsInteractor){
        this.settingsInteractor = settingsInteractor;
    }

    @Override
    protected void onViewReady() {
        showSettings();
    }

    private void showSettings() {
        view.showProgress();
        settingsInteractor.getAppSettings(new Carry<ArrayList<String>>() {
            @Override
            public void onSuccess(ArrayList<String> result) {

                ////////////////////////
                view.showSettings(result);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {

                /////////////////////////
                view.showError(throwable.getMessage());
                view.hideProgress();
            }
        });

    }
}
