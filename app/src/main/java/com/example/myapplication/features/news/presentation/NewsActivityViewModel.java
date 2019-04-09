package com.example.myapplication.features.news.presentation;

import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.news.domain.NewsInteractor;
import com.example.myapplication.features.news.domain.NewsInteractorImpl;
import com.example.myapplication.network.Carry;

import java.util.ArrayList;

public class NewsActivityViewModel extends BaseViewModel<NewsListView> {

    private NewsInteractor interactor;

    NewsActivityViewModel(){
        interactor = new NewsInteractorImpl();
    }

    @Override
    protected void onViewReady() {
    }

    public void showNews(String news) {
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
}
