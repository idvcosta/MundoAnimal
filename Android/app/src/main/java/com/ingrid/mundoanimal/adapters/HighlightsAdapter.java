package com.ingrid.mundoanimal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.model.HighlightItem;

import java.util.List;

public class HighlightsAdapter extends RecyclerView.Adapter<HighlightsAdapter.HighlightHolder> {
    public class HighlightHolder extends RecyclerView.ViewHolder {
        private final TextView tvTitle;

        public HighlightHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

    private List<HighlightItem> highlightItems;

    @NonNull
    @Override
    public HighlightHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_highlight_item, parent, false);

        return new HighlightHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HighlightHolder holder, int position) {
        HighlightItem highlightItem = highlightItems.get(position);

        holder.tvTitle.setText(highlightItem.getTitle());
    }

    @Override
    public int getItemCount() {
        return highlightItems == null ? 0 : highlightItems.size();
    }

    public void updateHighlights(List<HighlightItem> highlightItems) {
        this.highlightItems = highlightItems;
        notifyDataSetChanged();
    }
}
