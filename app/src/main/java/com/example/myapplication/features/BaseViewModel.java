package com.example.myapplication.features;

import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

public class BaseViewModel <View extends InterfaceView> {

    protected View view;

    public void attachView(View view) {
        this.view = view;
        onViewReady();
    }

    public void detachView() {
        view = null;
    }

    protected void onViewReady() {
    }
}
