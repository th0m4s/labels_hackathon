package dev.th0m4s.labelshackathon.db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

@Entity(tableName = "results")
public class APIResult {
    @PrimaryKey
    @NonNull
    public String code;

    @ColumnInfo(name = "last_scanned")
    public Date lastScanned = new Date();

    @ColumnInfo(name = "nutriscore")
    public String nutriscore;

    @ColumnInfo(name = "nutriments")
    public JSONObject nutriments;

    public JSONObject getNutrientLevels() {
        return nutrientLevels;
    }

    public APIResult setNutrientLevels(JSONObject nutrientLevels) {
        this.nutrientLevels = nutrientLevels;
        return this;
    }

    @ColumnInfo(name = "nutrient_levels")
    public JSONObject nutrientLevels;

    @ColumnInfo(name = "nutriscore_data")
    public JSONObject nutriscoreDetails;

    public JSONObject getNutriments() {
        return nutriments;
    }

    public APIResult setNutriments(JSONObject nutriments) {
        this.nutriments = nutriments;
        return this;
    }

    public JSONObject getNutriscoreDetails() {
        return nutriscoreDetails;
    }

    public APIResult setNutriscoreDetails(JSONObject nutriscoreDetails) {
        this.nutriscoreDetails = nutriscoreDetails;
        return this;
    }

    @ColumnInfo(name = "product_name")
    public String productName;

    @ColumnInfo(name = "generic_name")
    public String genericName;

    @ColumnInfo(name = "brands")
    public String brands;

    public String[] getBrands() {
        return brands.split(",");
    }

    public String getBrandsInline() {
        return mergeArray(", ", getBrands());
    }

    private String mergeArray(String sep, String[] arr) {
        String res = "";
        if(arr.length > 0) {
            for(int i = 0 ; i < arr.length - 1; i++) res += arr[i] + ", ";
            res += arr[arr.length-1];
        }

        return res;
    }

    @ColumnInfo(name = "ecoscore")
    public String ecoscore;

    @ColumnInfo(name = "labels_hierarchy")
    public String labelsHierarchy;

    public String[] getLabelsHierarchy() {
        return labelsHierarchy.split(",");
    }

    @ColumnInfo(name = "labels")
    public String labels;

    public String[] getLabels() {
        return labels.trim().split(",");
    }

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    public APIResult setCode(String code) {
        this.code = code;
        return this;
    }

    public APIResult setNutriscore(String nutriscore) {
        this.nutriscore = nutriscore;
        return this;
    }

    public APIResult setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public APIResult setGenericName(String genericName) {
        this.genericName = genericName;
        return this;
    }

    public APIResult setBrands(String brands) {
        this.brands = brands;
        return this;
    }

    public APIResult setEcoscore(String ecoscore) {
        this.ecoscore = ecoscore;
        return this;
    }

    public APIResult setLabelsHierarchy(String labelsHierarchy) {
        this.labelsHierarchy = labelsHierarchy;
        return this;
    }

    public APIResult setLabelsHierarchy(JSONArray jsonArray) {
        return setLabelsHierarchy(mergeArray(",", toStringArray(jsonArray)));
    }

    private String[] toStringArray(JSONArray array) {
        if(array==null)
            return null;

        String[] arr=new String[array.length()];
        for(int i=0; i<arr.length; i++) {
            arr[i]=array.optString(i);
        }
        return arr;
    }

    public APIResult setLabels(String labels) {
        this.labels = labels;
        return this;
    }

    public APIResult setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
