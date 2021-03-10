package com.ingrid.mundoanimal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.model.Services;

import java.util.List;


public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ServicesHolder> {
    public class ServicesHolder extends RecyclerView.ViewHolder {

        private final TextView tvTitle;
        private final TextView tvPrice;
        private final ImageView ivItem;

        public ServicesHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            ivItem = itemView.findViewById(R.id.ivItem);
        }

    }

    private List<Services> services;

    @NonNull
    @Override
    public ServicesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_service, parent, false);

        return new ServicesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesHolder holder, int position) {
        Services service = services.get(position);

        holder.tvTitle.setText(service.getName());
        holder.tvPrice.setText(service.getPrice());
        //////////////////
        Glide
                .with(holder.itemView.getContext())
                .load("https://mundo-animal-server.appspot.com/" + service.getImage())
                .into(holder.ivItem);
    }

    @Override
    public int getItemCount() {
        return services == null ? 0 : services.size();
    }

    public void updateItems(List<Services> services) {
        this.services = services;
        notifyDataSetChanged();
    }
}
