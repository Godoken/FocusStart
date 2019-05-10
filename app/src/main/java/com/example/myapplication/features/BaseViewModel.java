package com.example.myapplication.features;

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
