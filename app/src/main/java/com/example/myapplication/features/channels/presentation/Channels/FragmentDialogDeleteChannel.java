package com.example.myapplication.features.channels.presentation.Channels;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.features.channels.domain.model.Channel;

import androidx.fragment.app.DialogFragment;

public class FragmentDialogDeleteChannel extends DialogFragment {

    private Channel channel;

    private TextView message;
    private Button delete_button;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_dialog_delete_channel, null);

        message = v.findViewById(R.id.dialog_message);
        delete_button = v.findViewById(R.id.delete);

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ChannelActivityViewModel channelActivityViewModel = new ChannelActivityViewModel();
                channelActivityViewModel.onDeleteChannelClicked(channel);
                dismiss();
            }
        });
        return v;
    }


    public void onDismiss(DialogInterface dialogInterface) {
        super.dismiss();
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
