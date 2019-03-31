package com.example.myapplication.features.channels.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.features.BaseActivity;
import com.example.myapplication.features.MvpPresenter;
import com.example.myapplication.features.MvpView;
import com.example.myapplication.features.channels.domain.model.Channel;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChannelActivity extends BaseActivity implements ChannelListView {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button createChannelButton;
    private ChannelAdapter adapter;

    private ChannelListPresenter presenter;

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
                presenter.onCreateChannelClicked();
            }
        });

        adapter = new ChannelAdapter(this, new ChannelAdapter.SelectChannelListener() {
            @Override
            public void onChannelSelect(Channel channel) {
                presenter.onChannelSelected(channel);
            }

            @Override
            public void onChannelLongClick(Channel channel) {
                presenter.onChannelLongClicked(channel);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected MvpPresenter<ChannelListView> getPresenter() {

        presenter = PresenterFactory.createPresenter(this);
        return presenter;
    }

    @Override
    protected <T extends MvpView> T getMvpView() {
        return null;
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
}
