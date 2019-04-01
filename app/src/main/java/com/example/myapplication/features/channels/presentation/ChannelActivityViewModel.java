package com.example.myapplication.features.channels.presentation;

import android.view.View;
import android.widget.ProgressBar;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.channels.domain.model.Channel;

import androidx.recyclerview.widget.RecyclerView;

public class ChannelActivityViewModel extends BaseViewModel<ChannelListView> {


    public void onCreateChannelClicked() {
    }

    public void onChannelSelected(Channel channel) {

        view.showProgress();
        /*interactor.loadBook(channel.getId(), new Carry<Book>() {

            @Override
            public void onSuccess(Book result) {
                view.hideProgress();
                // do something
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }

        });*/
    }

    public void onChannelLongClicked(Channel channel) {
    }

}
