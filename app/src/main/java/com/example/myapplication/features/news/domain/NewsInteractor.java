package com.example.myapplication.features.news.domain;

import com.example.myapplication.support.Carry;

import java.util.ArrayList;

public interface NewsInteractor {

    void convertToArrayList(String news, Carry<ArrayList<String>> carry);

}
