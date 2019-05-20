package com.example.myapplication.features.channels.presentation.Channels;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import com.example.myapplication.R;
import com.example.myapplication.features.channels.domain.model.Channel;

public class FragmentDialogCreateChannel extends DialogFragment {

    private EditText edit_url;
    private EditText edit_name;
    private Button create_button;
    private Button cancel_create_button;

    private String url;
    private String name;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        setRetainInstance(true);

        View v = inflater.inflate(R.layout.fragment_dialog_create_channel, null);

        edit_url = v.findViewById(R.id.dialog_url);
        edit_name = v.findViewById(R.id.dialog_name);
        create_button = v.findViewById(R.id.create);
        cancel_create_button = v.findViewById(R.id.cancel_create);

        create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((!edit_name.getText().toString().equals("")) | (!edit_url.getText().toString().equals(""))){

                    FragmentViewModel fragmentViewModel = ViewModelFactory.createFragmentViewModel();

                    url = edit_url.getText().toString();
                    name = edit_name.getText().toString();

                    Channel channel = new Channel(name, "", url);
                    fragmentViewModel.onCreateChannelClicked(channel);
                }
                dismiss();
            }
        });

        cancel_create_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        return v;
    }

    public void setUrl(String url){
        this.url = url;
    }


    public void onDismiss(DialogInterface dialogInterface) {
        super.dismiss();
    }

    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
    }

    public void onDestroyView() {

        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);

        super.onDestroyView();
    }
}
