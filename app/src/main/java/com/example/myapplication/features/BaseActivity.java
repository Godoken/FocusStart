package com.example.myapplication.features;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;

import com.example.myapplication.R;

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
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        String style = sp.getString("STYLE_SETTINGS_KEY", "default");

        switch (style){
            case "dark":
                setTheme(R.style.Theme_AppCompat_Light_DarkActionBar);
                break;

            case "rainbow":
                setTheme(R.style.AppRainbow);
                break;

            case "default":
                setTheme(R.style.AppTheme);
                break;
        }
    }

}
