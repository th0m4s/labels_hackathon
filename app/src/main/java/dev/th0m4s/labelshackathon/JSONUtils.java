package dev.th0m4s.labelshackathon;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONUtils {
    public static String getStringOrDefault(JSONObject object, String name, String defaultValue) {
        try {
            return object.getString(name);
        } catch(JSONException e) {
            return defaultValue;
        }
    }

    public static JSONObject getJSONObjectOrDefault(JSONObject object, String name, JSONObject defaultValue) {
        try {
            return object.getJSONObject(name);
        } catch (JSONException e) {
            return defaultValue;
        }
    }
}
