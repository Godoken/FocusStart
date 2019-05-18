package com.example.myapplication.features.settings.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.features.BaseActivity;
import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.InterfaceView;

public class SettingsActivity extends BaseActivity implements SettingsListView {

    private SettingsActivityViewModel settingsActivityViewModel;

    private ProgressBar progressBar;

    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;

    private CheckBox checkBox_dark;
    private CheckBox checkBox_rainbow;
    private CheckBox checkBox_default;

    @Override
    protected BaseViewModel<SettingsListView> getViewModel() {
        settingsActivityViewModel = ViewModelFactory.createSettingsActivityViewModel();
        return settingsActivityViewModel;
    }

    @Override
    protected InterfaceView getInterfaceView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        initView();
    }

    private void initView() {

        progressBar = findViewById(R.id.settings_progress);

        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    settingsActivityViewModel.onSettingsChanged("30");
                } else {
                    settingsActivityViewModel.onSettingsChanged("0");
                }

            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    settingsActivityViewModel.onSettingsChanged("120");
                } else {
                    settingsActivityViewModel.onSettingsChanged("0");
                }

            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    settingsActivityViewModel.onSettingsChanged("360");
                } else {
                    settingsActivityViewModel.onSettingsChanged("0");
                }

            }
        });

        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    settingsActivityViewModel.onSettingsChanged("0");
                } else {
                }

            }
        });

        checkBox_dark = findViewById(R.id.checkBox_dark);
        checkBox_rainbow = findViewById(R.id.checkBox_rainbow);
        checkBox_default = findViewById(R.id.checkBox_default);

        checkBox_dark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    settingsActivityViewModel.onStyleChanged("dark");
                } else {
                    settingsActivityViewModel.onStyleChanged("default");
                }

            }
        });

        checkBox_rainbow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    settingsActivityViewModel.onStyleChanged("rainbow");
                } else {
                    settingsActivityViewModel.onStyleChanged("default");
                }

            }
        });

        checkBox_default.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    settingsActivityViewModel.onStyleChanged("default");
                } else {
                }

            }
        });
    }

    @Override
    public void showSettings(String setting) {

        switch (setting){

            case "30":
                checkBox1.setChecked(true);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                break;

            case "120":
                checkBox2.setChecked(true);
                checkBox1.setChecked(false);
                checkBox3.setChecked(false);
                checkBox4.setChecked(false);
                break;

            case "360":
                checkBox3.setChecked(true);
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox4.setChecked(false);
                break;

            case "0":
                checkBox4.setChecked(true);
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                break;
        }
    }

    @Override
    public void showStyleSettings(String setting) {
        switch (setting){

            case "dark":
                checkBox_dark.setChecked(true);
                checkBox_rainbow.setChecked(false);
                checkBox_default.setChecked(false);
                break;

            case "rainbow":
                checkBox_dark.setChecked(false);
                checkBox_rainbow.setChecked(true);
                checkBox_default.setChecked(false);
                break;

            case "default":
                checkBox_dark.setChecked(false);
                checkBox_rainbow.setChecked(false);
                checkBox_default.setChecked(true);
                break;
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
