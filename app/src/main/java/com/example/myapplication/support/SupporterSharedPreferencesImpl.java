package com.example.myapplication.support;

import android.content.SharedPreferences;

import com.example.myapplication.R;

public class SupporterSharedPreferencesImpl implements SupporterSharedPreferences {

    private SharedPreferences sharedPreferences;

    private final String styleKey = "STYLE_SETTINGS_KEY";
    private final String periodKey = "PERIOD_SETTINGS_KEY";
    private final String periodicTag = "TAG_PERIODIC_WORKER_NET";

    private String periodRadioGroupKey = "period";
    private String styleRadioGroupKey = "style";

    private final String firstStyleValue = "dark";
    private final String secondStyleValue = "rainbow";
    private final String thirdStyleValue = "default";

    private final int firstPeriodValue = 30;
    private final int secondPeriodValue = 120;
    private final int thirdPeriodValue = 360;
    private final int fourthPeriodValue = 0;

    public SupporterSharedPreferencesImpl(SharedPreferences sharedPreferences){
        this.sharedPreferences = sharedPreferences;
    }

    public SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }

    public String getFirstStyleValue() {
        return firstStyleValue;
    }

    public String getSecondStyleValue() {
        return secondStyleValue;
    }

    public String getThirdStyleValue() {
        return thirdStyleValue;
    }

    private String getStylePreferences(){
        return sharedPreferences.getString(styleKey, thirdStyleValue);
    }

    public int getTheme(){
        int theme;
        String style = getStylePreferences();

        switch (style){
            case firstStyleValue:
                theme = R.style.Theme_AppCompat_Light_DarkActionBar;
                break;

            case secondStyleValue:
                theme = R.style.AppRainbow;
                break;

            case thirdStyleValue:
                theme = R.style.AppTheme;
                break;

            default:
                theme = R.style.AppTheme;
                break;
        }
        return theme;
    }

    public int getFirstPeriodValue() {
        return firstPeriodValue;
    }

    public int getSecondPeriodValue() {
        return secondPeriodValue;
    }

    public int getThirdPeriodValue() {
        return thirdPeriodValue;
    }

    public int getFourthPeriodValue() {
        return fourthPeriodValue;
    }

    public String getStyleKey() {
        return styleKey;
    }

    public String getPeriodKey() {
        return periodKey;
    }

    public String getPeriodicTag() {
        return periodicTag;
    }

    public String getPeriodRadioGroupKey() {
        return periodRadioGroupKey;
    }

    public String getStyleRadioGroupKey() {
        return styleRadioGroupKey;
    }
}
