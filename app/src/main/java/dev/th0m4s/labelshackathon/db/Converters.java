package dev.th0m4s.labelshackathon.db;

import androidx.room.TypeConverter;

import org.json.JSONObject;

import java.util.Date;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static JSONObject fromString(String value) {
        try {
            return value == null ? null : new JSONObject(value);
        } catch(Exception e) {
            return null;
        }
    }

    @TypeConverter
    public static String jsonObjectToString(JSONObject obj) {
        return obj == null ? null : obj.toString();
    }
}
