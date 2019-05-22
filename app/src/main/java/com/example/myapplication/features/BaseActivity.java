package com.example.myapplication.features;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.example.myapplication.App;

public abstract class BaseActivity extends AppCompatActivity implements LifecycleOwner {

    protected abstract <T extends InterfaceView> BaseViewModel<T> getViewModel();

    protected abstract <T extends InterfaceView> T getInterfaceView();

    private BaseViewModel<InterfaceView> baseViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        checkAppTheme();
        super.onCreate(savedInstanceState);
        baseViewModel = getViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        baseViewModel.attachView(getInterfaceView());
    }

    @Override
    protected void onStop() {
        super.onStop();
        baseViewModel.detachView();
    }

    private void checkAppTheme(){
        int theme = App.getSupporterSharedPreferences().getTheme();
        setTheme(theme);
    }

}
