package com.example.myapplication.features.channels.data.room;

import com.example.myapplication.features.channels.domain.model.Channel;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ChannelDao {

    @Insert
    Long[] insertAll(ArrayList<Channel> channelList);

    @Insert
    void insert(Channel channel);

    @Delete
    void delete(Channel channel);

    @Query("SELECT * FROM channel")
    LiveData<List<Channel>> getAllChannels();

    @Query("SELECT * FROM channel")
    List<Channel> getAllChannelsDead();

    @Query("SELECT * FROM channel WHERE url = :channelUrl")
    Channel getChannelByUrl(String channelUrl);

    @Update
    void updateAllChannels(List<Channel> channelList);
}
