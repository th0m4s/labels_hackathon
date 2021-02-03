package dev.th0m4s.labelshackathon.adapters;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.th0m4s.labelshackathon.R;
import dev.th0m4s.labelshackathon.labels.ProductLabel;

public class NutrimentsAdapter extends RecyclerView.Adapter<NutrimentsAdapter.ViewHolder> {
    private Resources resources;
    private List<Nutriment> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameTextView;
        public final TextView valueTextView;
        public final ImageView logoImageView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.nutrimentName);
            valueTextView = (TextView) view.findViewById(R.id.nutrimentValue);
            logoImageView = (ImageView) view.findViewById(R.id.nutrimentImage);
        }
    }

    public NutrimentsAdapter(Resources resources, List<Nutriment> dataSet) {
        this.resources = resources;
        localDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.nutriment_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Nutriment item = localDataSet.get(position);
        viewHolder.nameTextView.setText(item.name + " :");
        viewHolder.valueTextView.setText(item.value + "g");
        viewHolder.logoImageView.setImageDrawable(resources.getDrawable(item.getLevelDrawableInt()));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class Nutriment {
        String level;

        public Nutriment(String level, String name, double value) {
            this.level = level;
            this.name = name;
            this.value = value;
        }

        public String name;
        public double value;

        public int getLevelDrawableInt() {
            switch(level.toLowerCase()) {
                case "high":
                    return R.drawable.high;
                case "moderate":
                    return R.drawable.moderate;
                case "low":
                    return R.drawable.low;
                default: return 0;
            }
        }
    }
}