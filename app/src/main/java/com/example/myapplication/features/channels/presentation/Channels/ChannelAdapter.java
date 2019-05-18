package com.example.myapplication.features.channels.presentation.Channels;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.features.channels.domain.model.Channel;

import java.util.ArrayList;
import java.util.List;

final class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.ChannelHolder> {

    private final List<Channel> channels = new ArrayList<>();
    private final LayoutInflater inflater;
    private final SelectChannelListener selectChannelListener;

    ChannelAdapter(Context context, SelectChannelListener selectChannelListener) {
        inflater = LayoutInflater.from(context);
        this.selectChannelListener = selectChannelListener;
    }

    @NonNull
    @Override
    public ChannelHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.channel_item, parent, false);

        return new ChannelHolder(itemView, selectChannelListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ChannelHolder holder, int position) {
        holder.bind(channels.get(position));
    }

    @Override
    public int getItemCount() {
        return channels.size();
    }

    public void setChannels(List<Channel> channelList) {
        channels.clear();
        channels.addAll(channelList);
        notifyDataSetChanged();
    }

    class ChannelHolder extends RecyclerView.ViewHolder {

        private final TextView channelNameView;
        private final TextView channelUrlView;
        private final SelectChannelListener selectChannelListener;

        ChannelHolder(View view, SelectChannelListener selectChannelListener) {
            super(view);
            this.selectChannelListener = selectChannelListener;
            channelNameView = view.findViewById(R.id.channel_item_name);
            channelUrlView = view.findViewById(R.id.channel_item_url);
        }

        void bind(final Channel channel) {
            channelNameView.setText(channel.getName());
            channelUrlView.setText(channel.getUrl());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectChannelListener.onChannelSelect(channel);
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    selectChannelListener.onChannelLongClick(channel);
                    return true;
                }
            });

        }

    }

    interface SelectChannelListener {

        void onChannelSelect(Channel channel);

        void onChannelLongClick(Channel channel);

    }

}
