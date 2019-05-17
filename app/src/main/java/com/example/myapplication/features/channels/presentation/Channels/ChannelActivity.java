package com.example.myapplication.features.channels.presentation.Channels;

import android.content.DialogInterface;
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
import com.example.myapplication.features.settings.presentation.SettingsActivity;

import java.util.List;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ChannelActivity extends BaseActivity implements ChannelListView {


    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private Button createChannelButton;
    private Button settingsAppButton;
    private ChannelAdapter adapter;

    private ChannelActivityViewModel channelActivityViewModel;

    private Intent intent;
    private String action;
    private String data;

    @Override
    protected BaseViewModel<ChannelListView> getViewModel() {

        intent = getIntent();
        action = intent.getAction();
        data = intent.getDataString();

        channelActivityViewModel = ViewModelFactory.createViewModel();
        channelActivityViewModel.setAction(action);
        channelActivityViewModel.setUrl(data);

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
        settingsAppButton = findViewById(R.id.settings_button);

        createChannelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channelActivityViewModel.onCreateChannelActivityClicked();
            }
        });

        settingsAppButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                channelActivityViewModel.onSettingsAppActivityClicked();
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

    @Override
    public void createChannel() {
        FragmentDialogCreateChannel fragmentDialogCreateChannel = new FragmentDialogCreateChannel();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentDialogCreateChannel.show(fragmentManager, "create_channel");
    }

    @Override
    public void deleteChannel(Channel channel) {
        FragmentDialogDeleteChannel fragmentDialogDeleteChannel = new FragmentDialogDeleteChannel();
        fragmentDialogDeleteChannel.setChannel(channel);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentDialogDeleteChannel.show(fragmentManager, "delete_channel");
    }

    @Override
    public void startAppSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        channelActivityViewModel.onBackPressed();
    }

    @Override
    public void openQuitDialog() {
        AlertDialog.Builder quitDialog = new AlertDialog.Builder(
                this);
        quitDialog.setTitle(R.string.on_back_pressed);

        quitDialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        quitDialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        quitDialog.show();
    }
}
