package com.example.myapplication.features.news.domain;

import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;
import com.example.myapplication.network.Carry;

import java.util.ArrayList;
import java.util.List;

public interface NewsInteractor {

    void convertToArrayList(String news, Carry<ArrayList<String>> carry);

}
