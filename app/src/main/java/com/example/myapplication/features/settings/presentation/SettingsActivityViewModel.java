package com.example.myapplication.features.settings.presentation;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.features.settings.domain.SettingsInteractor;
import com.example.myapplication.network.Carry;

public class SettingsActivityViewModel extends BaseViewModel<SettingsListView> {

    private SettingsInteractor settingsInteractor;


    SettingsActivityViewModel(SettingsInteractor settingsInteractor){
        this.settingsInteractor = settingsInteractor;
    }

    @Override
    protected void onViewReady() {
        showSettings();
        showStyleSettings();
    }

    private void showSettings() {
        view.showProgress();
        settingsInteractor.getAppSettings(new Carry<String>() {
            @Override
            public void onSuccess(String result) {
                view.loadStylePreferences(Integer.parseInt(result));
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
                view.hideProgress();
            }
        });

    }

    private void showStyleSettings() {
        view.showProgress();
        settingsInteractor.getStyleAppSettings(new Carry<String>() {
            @Override
            public void onSuccess(String style) {
                view.loadPeriodicPreferences(Integer.parseInt(style));
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
                view.hideProgress();
            }
        });

    }

    public void onSettingsChanged(String settings, int index){

        view.showProgress();
        settingsInteractor.setAppSettings(settings, index, new Carry<Success>() {
            @Override
            public void onSuccess(Success result) {
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
                view.hideProgress();
            }
        });
    }

    public void onStyleChanged(String style, int index) {
        view.showProgress();
        settingsInteractor.setStyleAppSettings(style, index, new Carry<Success>() {
            @Override
            public void onSuccess(Success result) {
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
                view.hideProgress();
            }
        });
    }
}
