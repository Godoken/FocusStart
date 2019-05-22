package com.example.myapplication.support;

import android.content.SharedPreferences;

public interface SupporterSharedPreferences {
    int getFirstPeriodValue();

    int getSecondPeriodValue();

    int getThirdPeriodValue();

    int getFourthPeriodValue();

    String getFirstStyleValue();

    String getSecondStyleValue();

    String getThirdStyleValue();

    int getTheme();

    SharedPreferences getSharedPreferences();

    String getStyleKey();

    String getPeriodKey();

    String getPeriodicTag();

    String getPeriodRadioGroupKey();

    String getStyleRadioGroupKey();
}
