package com.example.myapplication.features.settings.presentation;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.features.settings.domain.SettingsInteractor;
import com.example.myapplication.support.Carry;

public class SettingsActivityViewModel extends BaseViewModel<SettingsListView> {

    private SettingsInteractor settingsInteractor;


    SettingsActivityViewModel(SettingsInteractor settingsInteractor){
        this.settingsInteractor = settingsInteractor;
    }

    @Override
    protected void onViewReady() {
        showPeriodSettings();
        showStyleSettings();
    }

    private void showPeriodSettings() {
        view.showProgress();
        settingsInteractor.getPeriodAppSettings(new Carry<Integer>() {
            @Override
            public void onSuccess(Integer period) {
                view.loadPeriodicPreferences(period);
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
        settingsInteractor.getStyleAppSettings(new Carry<Integer>() {
            @Override
            public void onSuccess(Integer style) {
                view.loadStylePreferences(style);
                view.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.showError(throwable.getMessage());
                view.hideProgress();
            }
        });

    }

    public void onPeriodSettingsChanged(int period, int index){

        view.showProgress();
        settingsInteractor.setPeriodAppSettings(period, index, new Carry<Success>() {
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
