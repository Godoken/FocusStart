package com.example.myapplication.features.channels.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication.features.channels.domain.model.Channel;

@Database(entities = {Channel.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ChannelDao getChannelDao();
}
