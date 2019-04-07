package com.example.myapplication.features.channels.data.Room;

import com.example.myapplication.features.channels.domain.model.Channel;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ChannelDao {

    @Insert
    void insertAll(Channel... channels);

    @Delete
    void delete(Channel channel);

    @Query("SELECT * FROM channel")
    LiveData<List<Channel>> getAllChannels();

    @Query("SELECT * FROM channel WHERE url = :channelUrl")
    LiveData<Channel> getChannelByUrl(String channelUrl);
}
