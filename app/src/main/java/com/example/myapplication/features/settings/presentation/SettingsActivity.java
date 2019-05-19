package com.example.myapplication.features.settings.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.features.BaseActivity;
import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.InterfaceView;

public class SettingsActivity extends BaseActivity implements SettingsListView {

    private SettingsActivityViewModel settingsActivityViewModel;

    private ProgressBar progressBar;

    private RadioGroup periodicRadioGroup;
    private RadioGroup styleRadioGroup;

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

        periodicRadioGroup = findViewById(R.id.periodic_radio_group);

        periodicRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radio_30:
                        settingsActivityViewModel.onSettingsChanged("30");
                        break;

                    case R.id.radio_2:
                        settingsActivityViewModel.onSettingsChanged("120");
                        break;

                    case R.id.radio_6:
                        settingsActivityViewModel.onSettingsChanged("360");
                        break;

                    case R.id.radio_never:
                        settingsActivityViewModel.onSettingsChanged("0");
                        break;

                    default:
                        break;

                }
            }
        });

        styleRadioGroup =  findViewById(R.id.style_radio_group);

        styleRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case -1:
                        Toast.makeText(getApplicationContext(), "Ничего не выбрано",
                                Toast.LENGTH_SHORT).show();
                        break;

                    case R.id.radio_dark:
                        settingsActivityViewModel.onStyleChanged("dark");
                        break;

                    case R.id.radio_rainbow:
                        settingsActivityViewModel.onStyleChanged("rainbow");
                        break;

                    case R.id.radio_default:
                        settingsActivityViewModel.onStyleChanged("default");
                        break;

                    default:
                        break;

                }
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
}
