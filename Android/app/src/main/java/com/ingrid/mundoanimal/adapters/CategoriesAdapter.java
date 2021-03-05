package com.ingrid.mundoanimal.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.model.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryHolder> {

    private CategorySelectedListener listener;

    public class CategoryHolder extends RecyclerView.ViewHolder {

        private final TextView tvCategoryName;

        public CategoryHolder(@NonNull View itemView) {
            super(itemView);

            tvCategoryName = itemView.findViewById(R.id.tvCategoryName);
        }

    }

    private List<Category> productsCategories;

    public CategoriesAdapter(CategorySelectedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_product_category, parent, false);
        view.setOnClickListener(source -> {
            Category category = (Category) source.getTag();
            listener.onCategorySelected(category);
        });

        return new CategoryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryHolder holder, int position) {
        Category category = productsCategories.get(position);

        holder.tvCategoryName.setText(category.getName());
        holder.itemView.setTag(category);
    }

    @Override
    public int getItemCount() {
        return productsCategories == null ? 0 : productsCategories.size();
    }

    public void updateCategories(List<Category> categories) {
        this.productsCategories = categories;
        notifyDataSetChanged();
    }
}
