package com.example.myapplication.features.news.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.features.BaseActivity;
import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.InterfaceView;

import java.util.ArrayList;


public class NewsActivity extends BaseActivity implements NewsListView {

    private NewsActivityViewModel newsActivityViewModel;

    private ListView listView;
    private ProgressBar progressBar;

    private Intent intent;
    private String news;

    @Override
    protected BaseViewModel<NewsListView> getViewModel() {
        newsActivityViewModel = new NewsActivityViewModel();
        return newsActivityViewModel;
    }

    @Override
    protected InterfaceView getInterfaceView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        initView();
    }

    private void initView() {

        progressBar = findViewById(R.id.news_progress);
        listView = findViewById(R.id.news_list_view);

        intent = getIntent();
        news = intent.getStringExtra("news");

        newsActivityViewModel.showNews(news);
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showNewsList(ArrayList<String> result) {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(NewsActivity.this, android.R.layout.simple_list_item_1, result);

        listView.setAdapter(arrayAdapter);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNewsNet(String news) {

    }
}
