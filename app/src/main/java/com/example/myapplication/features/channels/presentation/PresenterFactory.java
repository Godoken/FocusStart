package com.example.myapplication.features.channels.presentation;

import android.content.Context;

import com.example.myapplication.App;
import com.example.myapplication.features.channels.data.ChannelsApi;
import com.example.myapplication.features.channels.data.ChannelsDataSource;
import com.example.myapplication.features.channels.data.ChannelsDataSourceImpl;
import com.example.myapplication.features.channels.data.ChannelsRepositoryImpl;
import com.example.myapplication.features.channels.domain.ChannelsInteractor;
import com.example.myapplication.features.channels.domain.ChannelsInteractorImpl;
import com.example.myapplication.features.channels.domain.ChannelsRepository;
import com.example.myapplication.features.channels.domain.model.Channel;

final class PresenterFactory {

    static ChannelListPresenter createPresenter(Context context) {
        final ChannelsApi api = App.getRetrofitProvider(context)
                .getRetrofit()
                .create(ChannelsApi.class);

        final ChannelsDataSource dataSource = new ChannelsDataSourceImpl(api);
        final ChannelsRepository repository = new ChannelsRepositoryImpl(dataSource);
        final ChannelsInteractor interactor = new ChannelsInteractorImpl(repository);

        return new ChannelListPresenter(interactor);
    }

}