package com.example.myapplication.features.settings.presentation;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RadioButton;
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
        LoadPeriodicPreferences();

        periodicRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case -1:
                        break;

                    case R.id.radio_30:
                        settingsActivityViewModel.onSettingsChanged("30");

                        ///
                        RadioButton checkedRadioButton = (RadioButton) periodicRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex = periodicRadioGroup.indexOfChild(checkedRadioButton);
                        SavePeriodicPreferences("period", checkedIndex);
                        ///

                        break;

                    case R.id.radio_2:
                        settingsActivityViewModel.onSettingsChanged("120");

                        ///
                        RadioButton checkedRadioButton1 = (RadioButton) periodicRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex1 = periodicRadioGroup.indexOfChild(checkedRadioButton1);
                        SavePeriodicPreferences("period", checkedIndex1);
                        ///

                        break;

                    case R.id.radio_6:
                        settingsActivityViewModel.onSettingsChanged("360");

                        ///
                        RadioButton checkedRadioButton2 = (RadioButton) periodicRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex2 = periodicRadioGroup.indexOfChild(checkedRadioButton2);
                        SavePeriodicPreferences("period", checkedIndex2);
                        ///

                        break;

                    case R.id.radio_never:
                        settingsActivityViewModel.onSettingsChanged("0");

                        ///
                        RadioButton checkedRadioButton3 = (RadioButton) periodicRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex3 = periodicRadioGroup.indexOfChild(checkedRadioButton3);
                        SavePeriodicPreferences("period", checkedIndex3);
                        ///

                        break;

                    default:
                        break;

                }
            }
        });

        styleRadioGroup =  findViewById(R.id.style_radio_group);
        LoadStylePreferences();

        styleRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case -1:
                        break;

                    case R.id.radio_dark:
                        settingsActivityViewModel.onStyleChanged("dark");

                        ///
                        RadioButton checkedRadioButton = (RadioButton) styleRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex = styleRadioGroup.indexOfChild(checkedRadioButton);
                        SaveStylePreferences("sly", checkedIndex);
                        ///

                        break;

                    case R.id.radio_rainbow:
                        settingsActivityViewModel.onStyleChanged("rainbow");

                        ///
                        RadioButton checkedRadioButton1 = (RadioButton) styleRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex1 = styleRadioGroup.indexOfChild(checkedRadioButton1);
                        SaveStylePreferences("sly", checkedIndex1);
                        ///

                        break;

                    case R.id.radio_default:
                        settingsActivityViewModel.onStyleChanged("default");

                        ///
                        RadioButton checkedRadioButton2 = (RadioButton) styleRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex2 = styleRadioGroup.indexOfChild(checkedRadioButton2);
                        SaveStylePreferences("style", checkedIndex2);
                        ///

                        break;

                    default:
                        break;

                }
            }
        });

    }

    private void SaveStylePreferences(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    private void LoadStylePreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int savedRadioIndex = sharedPreferences.getInt(
                "style", 0);
        RadioButton savedCheckedRadioButton = (RadioButton) styleRadioGroup
                .getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }

    private void SavePeriodicPreferences(String key, int value) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putInt(key, value);
        edit.apply();
    }

    private void LoadPeriodicPreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int savedPeriodicIndex = sharedPreferences.getInt(
                "period", 0);
        RadioButton savedCheckedRadioButton = (RadioButton) periodicRadioGroup
                .getChildAt(savedPeriodicIndex);
        savedCheckedRadioButton.setChecked(true);
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
