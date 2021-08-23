package com.example.expandablelistview.roomdatabase;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {Product.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ProductDao taskDao();
}


