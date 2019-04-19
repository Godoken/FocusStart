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
        checkBox1 = findViewById(R.id.checkBox2);
        checkBox1 = findViewById(R.id.checkBox3);

        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                settingsActivityViewModel.onSettingsChanged("30");

            }
        });

        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                settingsActivityViewModel.onSettingsChanged("2");

            }
        });

        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                settingsActivityViewModel.onSettingsChanged("6");

            }
        });
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

    @Override
    public void showSettings(String setting) {

        // Working with UI

        switch (setting){

            case "30":
                checkBox1.setChecked(true);
                //
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                break;

            case "2":
                checkBox2.setChecked(true);
                //
                checkBox1.setChecked(false);
                checkBox3.setChecked(false);
                break;

            case "6":
                checkBox3.setChecked(true);
                //
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                break;

            case "":
                checkBox1.setChecked(false);
                checkBox2.setChecked(false);
                checkBox3.setChecked(false);
                break;
        }
    }
}
