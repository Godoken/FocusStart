package com.example.myapplication.features.channels.presentation.Channels;

import com.example.myapplication.features.channels.data.ChannelDataSourceImpl;
import com.example.myapplication.features.channels.data.ChannelsDataSource;
import com.example.myapplication.features.channels.data.ChannelsRepositoryImpl;
import com.example.myapplication.features.channels.domain.ChannelsInteractor;
import com.example.myapplication.features.channels.domain.ChannelsInteractorImpl;
import com.example.myapplication.features.channels.domain.ChannelsRepository;

final class ViewModelFactory {

    static ChannelActivityViewModel createViewModel(){

        final ChannelsDataSource channelsDataSource = new ChannelDataSourceImpl();
        final ChannelsRepository channelsRepository = new ChannelsRepositoryImpl(channelsDataSource);
        final ChannelsInteractor channelsInteractor = new ChannelsInteractorImpl(channelsRepository);

        return new ChannelActivityViewModel(channelsInteractor);

    }
}
