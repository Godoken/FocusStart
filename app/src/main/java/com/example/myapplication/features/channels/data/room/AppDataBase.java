package com.example.myapplication.features.channels.data.room;

import com.example.myapplication.features.channels.domain.model.Channel;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Channel.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    public abstract ChannelDao getChannelDao();
}
