package com.example.myapplication.features.channels.presentation.Channels;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.features.BaseActivity;
import com.example.myapplication.features.BaseViewModel;
import com.example.myapplication.features.InterfaceView;
import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.news.presentation.NewsActivity;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChannelActivity extends BaseActivity implements ChannelListView {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button createChannelButton;
    private ChannelAdapter adapter;

    private ChannelActivityViewModel channelActivityViewModel;

    @Override
    protected BaseViewModel<ChannelListView> getViewModel() {
        channelActivityViewModel = new ChannelActivityViewModel();
        return channelActivityViewModel;
    }

    @Override
    protected InterfaceView getInterfaceView() {
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.channels_activity);

        initView();
    }

    private void initView() {
        progressBar = findViewById(R.id.channels_progress);
        recyclerView = findViewById(R.id.channels_recycle_view);
        createChannelButton = findViewById(R.id.create_channel);

        createChannelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channelActivityViewModel.onCreateChannelClicked();
            }
        });

        adapter = new ChannelAdapter(this, new ChannelAdapter.SelectChannelListener() {
            @Override
            public void onChannelSelect(Channel channel) {
                channelActivityViewModel.onChannelSelected(channel);
            }

            @Override
            public void onChannelLongClick(Channel channel) {
                channelActivityViewModel.onChannelLongClicked(channel);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showChannelList(List<Channel> list) {
        adapter.setChannels(list);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showNews(String news) {
        Intent intent = new Intent(this, NewsActivity.class);
        intent.putExtra("news", news);
        startActivity(intent);
    }
}