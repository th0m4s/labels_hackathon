package dev.th0m4s.labelshackathon;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import dev.th0m4s.labelshackathon.adapters.NutrimentsAdapter;
import dev.th0m4s.labelshackathon.db.APIResult;
import dev.th0m4s.labelshackathon.db.ResultsDatabase;
import dev.th0m4s.labelshackathon.adapters.LabelsAdapter;
import dev.th0m4s.labelshackathon.labels.ProductLabel;

public class ResultFragment extends Fragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    public void loadResult(String code, boolean saveTime) {
        System.out.println("Searching results for " +  code);
        setLoading(true);

        APIResult result = ResultsDatabase.Instance.ResultDao().findByCode(code);
        if(result != null) {
            System.out.println("Result found in database");
            setLoading(false);
            showResult(result);

            if(saveTime) {
                new Thread(() -> {
                    ResultsDatabase.Instance.ResultDao().updateLastScan(code, new Date());
                }).start();
            }
        } else {
            System.out.println("Result not found in database, requesting from server");
            String url = "https://fr.openfoodfacts.org/api/v0/product/" + code + ".json";
            StringRequest request = new StringRequest(Request.Method.GET, url, (String response) -> {
                setLoading(false);
                try {
                    JSONObject responseObject = new JSONObject(response);

                    int status = responseObject.getInt("status");

                    if(status == 1) {
                        JSONObject product = responseObject.getJSONObject("product");

                        APIResult receivedResult = new APIResult()
                                .setBrands(product.getString("brands"))
                                .setCode(code)
                                .setGenericName(JSONUtils.getStringOrDefault(product,"generic_name", ""))
                                .setImageUrl(product.getString("image_url"))
                                .setProductName(product.getString("product_name"))
                                .setNutriments(product.getJSONObject("nutriments"))
                                .setNutrientLevels(product.getJSONObject("nutrient_levels"))
                                .setEcoscore(JSONUtils.getStringOrDefault(product.getJSONObject("ecoscore_data"), "grade", "none"))
                                .setNutriscore(JSONUtils.getStringOrDefault(product, "nutriscore_grade", "none"))
                                .setNutriscoreDetails(JSONUtils.getJSONObjectOrDefault(product, "nutriscore_data", null))
                                .setLabels(product.getString("labels"))
                                .setLabelsHierarchy(product.getJSONArray("labels_hierarchy"));

                        new Thread(() -> {
                            ResultsDatabase.Instance.ResultDao().insertAll(receivedResult);
                            if(MainActivity.Instance != null) MainActivity.Instance.UpdateHistory();
                        }).start();

                        showResult(receivedResult);
                    } else if(status == 0) {
                        showResult(null);
                        setStatus("Produit introuvable !");
                    } else {
                        showResult(null);
                        setStatus("Impossible de charger ce produit !");
                    }
                } catch(Exception e) {
                    loadError(e);
                }
            }, this::loadError);

            MainActivity.netRequestQueue.add(request);
        }
    }

    private void showResult(APIResult result) {
        getActivity().runOnUiThread(() -> {
            if(result == null) {
                getView().findViewById(R.id.resultImage).setVisibility(View.INVISIBLE);
                ((TextView)getView().findViewById(R.id.resultTitle)).setText("");
                ((TextView)getView().findViewById(R.id.resultBrands)).setText("");
                ((TextView)getView().findViewById(R.id.resultLabelsCount)).setText("");
                ((ImageView)getView().findViewById(R.id.resultNutriscore)).setImageDrawable(null);
                ((ImageView)getView().findViewById(R.id.resultEcoscore)).setImageDrawable(null);
                getView().findViewById(R.id.resultDetailsScroll).setVisibility(View.GONE);
            } else {
                ImageView imageView = (ImageView) getView().findViewById(R.id.resultImage);
                Picasso.get().load(result.imageUrl).into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        imageView.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                String name = result.productName;
                ((TextView)getView().findViewById(R.id.resultTitle)).setText(name == null || name.trim().length() == 0 ? result.genericName : name);
                ((TextView)getView().findViewById(R.id.resultBrands)).setText(result.getBrandsInline());
                ((ImageView)getView().findViewById(R.id.resultNutriscore)).setImageDrawable(getNutriscoreDrawable(getResources(), result.nutriscore));
                ((ImageView)getView().findViewById(R.id.resultEcoscore)).setImageDrawable(getEcoscoreDrawable(getResources(), result.ecoscore));

                List<ProductLabel> labels = getLabels(result);
                int labelsCount = labels.size();
                ((TextView)getView().findViewById(R.id.resultLabelsCount)).setText(labelsCount == 0 ? "" : (labelsCount == 1 ? "et 1 label" : "et " + labelsCount + " labels"));
                ((TextView)getView().findViewById(R.id.resultLabelsCount_exp)).setText(labelsCount == 0 ? "Ce produit n'a pas de labels :(" : (labelsCount == 1 ? "Ce produit possède 1 label vérifié :" : "Ce produit possède " + labelsCount + " labels vérifiés :"));

                getView().findViewById(R.id.resultDetailsScroll).setVisibility(View.VISIBLE);
                ((RecyclerView)getView().findViewById(R.id.recyclerViewLabels)).setAdapter(new LabelsAdapter(getResources(), labels));

                try {
                    List<NutrimentsAdapter.Nutriment> nutriments = getNutriments(result);
                    ((RecyclerView)getView().findViewById(R.id.recyclerViewNutriments)).setAdapter(new NutrimentsAdapter(getResources(), nutriments));
                } catch(JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public static List<NutrimentsAdapter.Nutriment> getNutriments(APIResult result) throws JSONException {
        List<NutrimentsAdapter.Nutriment> nutriments = new ArrayList<>();

        nutriments.add(new NutrimentsAdapter.Nutriment(result.nutrientLevels.getString("fat"), "Matières grasses / Lipides", result.nutriments.getDouble("fat_100g")));
        nutriments.add(new NutrimentsAdapter.Nutriment(result.nutrientLevels.getString("saturated-fat"), "Acides gras saturés", result.nutriments.getDouble("saturated-fat_100g")));
        nutriments.add(new NutrimentsAdapter.Nutriment(result.nutrientLevels.getString("sugars"), "Sucres", result.nutriments.getDouble("sugars_100g")));
        nutriments.add(new NutrimentsAdapter.Nutriment(result.nutrientLevels.getString("salt"), "Sel", result.nutriments.getDouble("salt_100g")));

        return nutriments;
    }

    public static List<ProductLabel> getLabels(APIResult result) {
        HashMap<String, ProductLabel> allLabels = ProductLabel.getAllLabels();
        List<ProductLabel> labels = new ArrayList<>();

        for(String labelCode : result.getLabelsHierarchy()) {
            String[] parts = labelCode.split(":");
            if(parts.length == 2 && parts[0].length() <= 3) labelCode = parts[1];

            if(allLabels.containsKey(labelCode))
                labels.add(allLabels.get(labelCode));
        }

        return labels;
    }

    public static Drawable getNutriscoreDrawable(Resources resources, String nutriscore) {
        int id = 0;
        switch(nutriscore.toLowerCase()) {
            case "a":
                id = R.drawable.nutriscore_a;
                break;
            case "b":
                id = R.drawable.nutriscore_b;
                break;
            case "c":
                id = R.drawable.nutriscore_c;
                break;
            case "d":
                id = R.drawable.nutriscore_d;
                break;
            case "e":
                id =  R.drawable.nutriscore_e;
                break;
            case "none":
                id = R.drawable.nutriscore_unknown;
                break;
        }

        return id == 0 ? null : resources.getDrawable(id);
    }

    public static Drawable getEcoscoreDrawable(Resources resources, String ecoscore) {
        int id = 0;
        switch(ecoscore.toLowerCase()) {
            case "a":
                id = R.drawable.ecoscore_a;
                break;
            case "b":
                id = R.drawable.ecoscore_b;
                break;
            case "c":
                id = R.drawable.ecoscore_c;
                break;
            case "d":
                id = R.drawable.ecoscore_d;
                break;
            case "e":
                id =  R.drawable.ecoscore_e;
                break;
            case "none":
                id = R.drawable.ecoscore_unknown;
                break;
        }

        return id == 0 ? null : resources.getDrawable(id);
    }

    public void setStatus(String status) {
        getActivity().runOnUiThread(() -> {
            ((TextView)getView().findViewById(R.id.resultStatus)).setText(status);
        });
    }

    private void loadError(Exception e) {
        setLoading(false);
        e.printStackTrace();
        setStatus("Une erreur est survenue !");
    }

    public void setLoading(boolean loading) {
        getActivity().runOnUiThread(() -> {
            getView().findViewById(R.id.resultDetailsScroll).setVisibility(View.GONE);
            // -> will be set visible if necessary in showResult
            getView().findViewById(R.id.resultProgressBar).setVisibility(loading ? View.VISIBLE : View.GONE);
            setStatus(loading ? "Chargement..." : "");
        });
    }
}