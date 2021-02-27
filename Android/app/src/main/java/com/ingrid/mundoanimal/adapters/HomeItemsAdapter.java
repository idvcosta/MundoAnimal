package com.ingrid.mundoanimal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.model.HomeItem;

import java.util.List;

public class HomeItemsAdapter extends RecyclerView.Adapter<HomeItemsAdapter.HomeItemHolder> {

    public class HomeItemHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvPrice;
        private final ImageView ivItem;

        public HomeItemHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivItem = itemView.findViewById(R.id.ivItem);
        }
    }

    private List<HomeItem> homeItems;

    @NonNull
    @Override
    public HomeItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_home_item, parent, false);

        return new HomeItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeItemHolder holder, int position) {
        HomeItem homeItem = homeItems.get(position);

        holder.tvTitle.setText(homeItem.getName());
        holder.tvPrice.setText(homeItem.getPrice());
        //////////////////
        Glide
                .with(holder.itemView.getContext())
                .load("https://mundo-animal-server.appspot.com/"+ homeItem.getImage())
                .into(holder.ivItem);

    }

    @Override
    public int getItemCount() {
        return homeItems == null ? 0 : homeItems.size();
    }

    public void updateItems(List<HomeItem> homeItems) {
        this.homeItems = homeItems;
        notifyDataSetChanged();
    }
}
