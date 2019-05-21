package com.example.myapplication.features.settings.presentation;

import android.os.Bundle;
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

        periodicRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){

                    case -1:
                        break;

                    case R.id.radio_30:

                        RadioButton checkedRadioButton = (RadioButton) periodicRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex = periodicRadioGroup.indexOfChild(checkedRadioButton);
                        settingsActivityViewModel.onSettingsChanged("30", checkedIndex);
                        break;

                    case R.id.radio_2:

                        ///
                        RadioButton checkedRadioButton1 = (RadioButton) periodicRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex1 = periodicRadioGroup.indexOfChild(checkedRadioButton1);
                        settingsActivityViewModel.onSettingsChanged("120", checkedIndex1);
                        break;

                    case R.id.radio_6:

                        ///
                        RadioButton checkedRadioButton2 = (RadioButton) periodicRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex2 = periodicRadioGroup.indexOfChild(checkedRadioButton2);
                        settingsActivityViewModel.onSettingsChanged("360", checkedIndex2);
                        break;

                    case R.id.radio_never:

                        RadioButton checkedRadioButton3 = (RadioButton) periodicRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex3 = periodicRadioGroup.indexOfChild(checkedRadioButton3);
                        settingsActivityViewModel.onSettingsChanged("0", checkedIndex3);
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
                        break;

                    case R.id.radio_dark:

                        RadioButton checkedRadioButton = (RadioButton) styleRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex = styleRadioGroup.indexOfChild(checkedRadioButton);
                        settingsActivityViewModel.onStyleChanged("dark", checkedIndex);
                        break;

                    case R.id.radio_rainbow:

                        RadioButton checkedRadioButton1 = (RadioButton) styleRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex1 = styleRadioGroup.indexOfChild(checkedRadioButton1);
                        settingsActivityViewModel.onStyleChanged("rainbow", checkedIndex1);
                        break;

                    case R.id.radio_default:

                        RadioButton checkedRadioButton2 = (RadioButton) styleRadioGroup
                                .findViewById(checkedId);
                        int checkedIndex2 = styleRadioGroup.indexOfChild(checkedRadioButton2);
                        settingsActivityViewModel.onStyleChanged("default", checkedIndex2);
                        break;

                    default:
                        break;

                }
            }
        });

    }

    @Override
    public void loadStylePreferences(int savedRadioIndex) {
        RadioButton savedCheckedRadioButton = (RadioButton) styleRadioGroup
                .getChildAt(savedRadioIndex);
        savedCheckedRadioButton.setChecked(true);
    }

    @Override
    public void loadPeriodicPreferences(int savedPeriodicIndex) {
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
