package com.example.myapplication.features.news.presentation;

import com.example.myapplication.features.news.domain.NewsInteractor;
import com.example.myapplication.features.news.domain.NewsInteractorImpl;

final class ViewModelFactory {

    static NewsActivityViewModel createNewsActivityViewModel(){

        final NewsInteractor newsInteractor = new NewsInteractorImpl();

        return new NewsActivityViewModel(newsInteractor);

    }
}
