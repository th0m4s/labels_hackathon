package dev.th0m4s.labelshackathon.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {APIResult.class}, version = 1)
public abstract class ResultsDatabase extends RoomDatabase {
    public abstract ResultDao ResultDao();
}
