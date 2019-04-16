package com.example.myapplication.features.news.presentation;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.news.domain.NewsInteractor;
import com.example.myapplication.features.news.domain.NewsInteractorImpl;
import com.example.myapplication.network.Carry;

import java.util.ArrayList;

public class NewsActivityViewModel extends BaseViewModel<NewsListView> {

    private NewsInteractor interactor;
    private String news;

    NewsActivityViewModel(NewsInteractor interactor){
        this.interactor = interactor;
    }

    @Override
    protected void onViewReady() {
        oshowNews();
    }

    public void oshowNews() {
        view.showProgress();
        interactor.convertToArrayList(news, new Carry<ArrayList<String>>() {
            @Override
            public void onSuccess(ArrayList<String> result) {
                view.hideProgress();
                view.showNewsList(result);
            }

            @Override
            public void onFailure(Throwable throwable) {
                view.hideProgress();
                view.showError(throwable.getMessage());
            }
        });
    }

    public void setNews(String news) {
        this.news = news;
    }
}
