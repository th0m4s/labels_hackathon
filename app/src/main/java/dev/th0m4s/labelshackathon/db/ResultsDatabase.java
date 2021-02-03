package dev.th0m4s.labelshackathon.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {APIResult.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class ResultsDatabase extends RoomDatabase {
    public abstract ResultDao ResultDao();

    public static ResultsDatabase Instance = null;

    public static int CachedCount = 0;

    public static void Prepare(Context context) {
        if(Instance == null) {
            Instance = Room.databaseBuilder(context, ResultsDatabase.class, "labels-hackathon").build();
        }
    }

    public static void UpdateCachedCount() {
        CachedCount = Instance.ResultDao().getCount();
    }
}
