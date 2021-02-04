package dev.th0m4s.labelshackathon.adapters;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import dev.th0m4s.labelshackathon.MainActivity;
import dev.th0m4s.labelshackathon.R;
import dev.th0m4s.labelshackathon.ResultActivity;
import dev.th0m4s.labelshackathon.db.APIResult;
import dev.th0m4s.labelshackathon.db.ResultsDatabase;
import dev.th0m4s.labelshackathon.labels.ProductLabel;

import static dev.th0m4s.labelshackathon.ResultFragment.getEcoscoreDrawable;
import static dev.th0m4s.labelshackathon.ResultFragment.getLabels;
import static dev.th0m4s.labelshackathon.ResultFragment.getNutriscoreDrawable;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private Resources resources;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewTitle;
        public final TextView textViewBrands;
        public final ImageView imageProduct;
        public final ImageView imageNutriscore;
        public final ImageView imageEcoscore;
        public final TextView textViewLabelsCount;

        public APIResult cachedResult;
        public boolean onClickSet = false;

        public ViewHolder(View view) {
            super(view);

            textViewTitle = (TextView) view.findViewById(R.id.historyTitle);
            textViewBrands = (TextView) view.findViewById(R.id.historyBrands);
            imageProduct = (ImageView) view.findViewById(R.id.historyImage);
            imageNutriscore = (ImageView) view.findViewById(R.id.historyNutriscore);
            imageEcoscore = (ImageView) view.findViewById(R.id.historyEcoscore);
            textViewLabelsCount = (TextView) view.findViewById(R.id.historyLabelsCount);

            view.setOnClickListener(v -> {
                Intent resultIntent = new Intent(MainActivity.Instance, ResultActivity.class);
                resultIntent.putExtra("code", cachedResult.code);
                MainActivity.Instance.startActivity(resultIntent);
            });
            view.setOnLongClickListener(v -> {
                AlertDialog dialog = new AlertDialog.Builder(MainActivity.Instance)
                        .setMessage("Voulez-vous supprimer ce produit de l'historique ?")
                        .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                new Thread(() -> {
                                    ResultsDatabase.Instance.ResultDao().delete(cachedResult);
                                    if(MainActivity.Instance != null) MainActivity.Instance.UpdateHistory();
                                }).start();
                            }
                        }).setNegativeButton("Non", null).show();
                return true;
            });
        }
    }

    public HistoryAdapter(Resources resources) {
        this.resources = resources;

        System.out.println("History of " + ResultsDatabase.CachedCount);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.history_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        new Thread(() -> {
            APIResult result = ResultsDatabase.Instance.ResultDao().getSortedIndex(position);
            viewHolder.cachedResult = result;

            MainActivity.Instance.runOnUiThread(() -> {
                Picasso.get().load(result.imageUrl).into(viewHolder.imageProduct, new Callback() {
                    @Override
                    public void onSuccess() {
                        viewHolder.imageProduct.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                String name = result.productName;
                viewHolder.textViewTitle.setText(name == null || name.trim().length() == 0 ? result.genericName : name);
                viewHolder.textViewBrands.setText(result.getBrandsInline());
                viewHolder.imageNutriscore.setImageDrawable(getNutriscoreDrawable(resources, result.nutriscore));
                viewHolder.imageEcoscore.setImageDrawable(getEcoscoreDrawable(resources, result.ecoscore));

                List<ProductLabel> labels = getLabels(result);
                int labelsCount = labels.size();
                viewHolder.textViewLabelsCount.setText(labelsCount == 0 ? "" : (labelsCount == 1 ? "et 1 label" : "et " + labelsCount + " labels"));
            });
        }).start();
    }

    @Override
    public int getItemCount() {
        return ResultsDatabase.CachedCount;
    }
}