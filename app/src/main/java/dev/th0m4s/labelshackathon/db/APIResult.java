package dev.th0m4s.labelshackathon.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;

@Entity
class APIResult {
    @PrimaryKey
    public String code;

    @ColumnInfo(name = "nutriscore")
    public String nutriscore;

    @ColumnInfo(name = "nutriscore_data")
    private String nutriscoreDetails;

    public JSONObject getNutriscoreDetails() throws JSONException {
        return new JSONObject(nutriscoreDetails);
    }

    @ColumnInfo(name = "product_name")
    public String productName;

    @ColumnInfo(name = "generic_name")
    public String genericName;

    @ColumnInfo(name = "brands")
    private String brands;

    public String[] getBrands() {
        return brands.split(",");
    }

    @ColumnInfo(name = "ecoscore")
    public String ecoscore;

    @ColumnInfo(name = "labels_hierarchy")
    private String labelsHierarchy;

    public String[] getLabelsHierarchy() {
        return labelsHierarchy.split(",");
    }

    @ColumnInfo(name = "labels")
    private String labels;

    public String[] getLabels() {
        return labels.trim().split(",");
    }

    @ColumnInfo(name = "image_url")
    public String imageUrl;
}
