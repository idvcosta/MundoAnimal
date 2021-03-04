package com.ingrid.mundoanimal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.model.ProductsCategory;

import java.util.List;

public class ProductsCategoriesAdapter extends RecyclerView.Adapter<ProductsCategoriesAdapter.ProductsCategoriesHolder> {

    public class ProductsCategoriesHolder extends RecyclerView.ViewHolder {

        private final TextView tvCategoryName;

        public ProductsCategoriesHolder(@NonNull View itemView) {
            super(itemView);

            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }

    }

    private List<ProductsCategory> productsCategories;

    @NonNull
    @Override
    public ProductsCategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_product_category, parent, false);

        return new ProductsCategoriesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsCategoriesHolder holder, int position) {
        ProductsCategory categoriesItems = productsCategories.get(position);
        holder.tvCategoryName.setText(categoriesItems.getName());
    }

    @Override
    public int getItemCount() {
        return productsCategories == null ? 0 : productsCategories.size();
    }

    public void updateCategories(List<ProductsCategory> categories) {
        this.productsCategories = categories;
        notifyDataSetChanged();
    }
}
