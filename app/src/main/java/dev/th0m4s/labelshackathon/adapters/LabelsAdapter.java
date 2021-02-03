package dev.th0m4s.labelshackathon.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dev.th0m4s.labelshackathon.R;
import dev.th0m4s.labelshackathon.labels.ProductLabel;

public class LabelsAdapter extends RecyclerView.Adapter<LabelsAdapter.ViewHolder> {
    private Resources resources;
    private List<ProductLabel> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView nameTextView;
        public final ImageView logoImageView;

        public ViewHolder(View view) {
            super(view);
            nameTextView = (TextView) view.findViewById(R.id.labelName);
            logoImageView = (ImageView) view.findViewById(R.id.labelImage);
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
        viewHolder.nameTextView.setText(item.getDisplayName());
        viewHolder.logoImageView.setImageDrawable(resources.getDrawable(item.getImage()));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}