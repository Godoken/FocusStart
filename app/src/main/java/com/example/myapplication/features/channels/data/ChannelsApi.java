package com.example.myapplication.features.channels.data;

import com.example.myapplication.features.channels.domain.model.Channel;
import com.example.myapplication.features.channels.domain.model.Success;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ChannelsApi {

    @GET("books")
    Call<List<Channel>> getChannelList();

    @GET("books/{id}")
    Call<Channel> getChannel(@Path("id") String id);

    @POST("books")
    Call<Channel> createChannel(@Body Channel book);

    @DELETE("books/{id}")
    Call<Success> deleteChannel(@Path("id") String id);

}
