package dev.th0m4s.labelshackathon.adapters;

import android.app.AlertDialog;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import dev.th0m4s.labelshackathon.R;
import dev.th0m4s.labelshackathon.labels.ProductLabel;

public class LabelsAdapter extends RecyclerView.Adapter<LabelsAdapter.ViewHolder> {
    private Resources resources;
    private List<ProductLabel> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameTextView;
        public final ImageView logoImageView;
        public final ImageView levelImageView;
        public ProductLabel cachedLabel;

        public ViewHolder(View view) {
            super(view);

            nameTextView = (TextView) view.findViewById(R.id.labelName);
            logoImageView = (ImageView) view.findViewById(R.id.labelImage);
            levelImageView = (ImageView) view.findViewById(R.id.labelLevel);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String desc = cachedLabel.getDesc();
                    if(desc == null || desc.length() == 0) {
                        Snackbar.make(v, "Détails sur ce label disponibles dans une future version de l'application.", BaseTransientBottomBar.LENGTH_SHORT).show();
                    } else {
                        AlertDialog dialog = new AlertDialog.Builder(v.getContext())
                                .setTitle(cachedLabel.getDisplayName())
                                .setIcon(cachedLabel.getImage())
                                .setMessage(desc)
                                .setPositiveButton("Fermer", null).show();
                    }
                }
            });
        }
    }

    public LabelsAdapter(Resources resources, List<ProductLabel> dataSet) {
        this.resources = resources;
        localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.label_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        ProductLabel item = localDataSet.get(position);
        viewHolder.cachedLabel = item;
        viewHolder.nameTextView.setText(item.getDisplayName());
        viewHolder.logoImageView.setImageDrawable(resources.getDrawable(item.getImage()));
        int levelDrawableId = getLevelId((item.getTrust()));
        viewHolder.levelImageView.setImageDrawable(levelDrawableId > 0 ? resources.getDrawable(levelDrawableId) : null);
    }

    private int getLevelId(int level) {
        switch(level) {
            case 0:
                return R.drawable.low;
            case 1:
                return R.drawable.moderate;
            case 2:
                return R.drawable.high;
            default: return 0;
        }
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}