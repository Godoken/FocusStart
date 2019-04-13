package com.example.myapplication.features.channels.presentation.Channels;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.features.channels.domain.model.Channel;

import androidx.fragment.app.DialogFragment;

public class FragmentDialogCreateChannel extends DialogFragment {

    EditText edit_url;
    EditText edit_name;
    Button create_button;

    String url;
    String name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.fragment_dialog_create_channel, null);

        edit_url = v.findViewById(R.id.dialog_url);
        edit_name = v.findViewById(R.id.dialog_name);
        create_button = v.findViewById(R.id.create);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((!edit_name.getText().toString().equals("")) | (!edit_url.getText().toString().equals(""))){

                    ChannelActivityViewModel channelActivityViewModel = new ChannelActivityViewModel();

                    //url = edit_url.getText().toString();
                    //url = "https://lenta.ru/rss/news.rss";
                    //url = "https://news.yandex.ru/Novosibirsk/index.rss";
                    url = "https://news.yandex.ru/society.rss";
                    name = edit_name.getText().toString();

                    Channel channel = new Channel(name, "", url);

                    channelActivityViewModel.onCreateChannelClicked(channel);
                } else {

                }

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
}
