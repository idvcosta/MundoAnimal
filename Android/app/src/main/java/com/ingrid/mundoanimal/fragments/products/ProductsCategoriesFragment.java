package com.ingrid.mundoanimal.fragments.products;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.activities.ProductActivity;
import com.ingrid.mundoanimal.adapters.CategorySelectedListener;
import com.ingrid.mundoanimal.adapters.ProductsCategoriesAdapter;
import com.ingrid.mundoanimal.model.ProductsCategory;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

public class ProductsCategoriesFragment extends Fragment {
    private SearchView seach;
    private ProgressBar progressBar;
    private TextView tvStatus;
    private RecyclerView rvProductsCategories;
    private ProductsViewModel productsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_categories, container, false);
        productsViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                MundoAnimalRepository repository = new MundoAnimalRepository(requireContext());
                return (T) new ProductsViewModel(repository);
            }
        }).get(ProductsViewModel.class);

        initViews(view);
        initObserves();

        return view;
    }

    private void initViews(View view) {
        seach = view.findViewById(R.id.search);
        progressBar = view.findViewById(R.id.progressBar);
        tvStatus = view.findViewById(R.id.tvStatus);
        rvProductsCategories = view.findViewById(R.id.rvProductsCategories);
    }

    private void initObserves() {
        productsViewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case LOADING_INITIAL_DATA:
                case SEARCHING:
                    progressBar.setVisibility(View.VISIBLE);
                    tvStatus.setVisibility(View.VISIBLE);
                    rvProductsCategories.setVisibility(View.GONE);
                    break;
                case INITIAL_DATA_LOADED:
                case SHOW_SEARCH_RESULTS:
                    progressBar.setVisibility(View.GONE);
                    tvStatus.setVisibility(View.GONE);
                    rvProductsCategories.setVisibility(View.VISIBLE);
                    break;
                case LOAD_INITIAL_DATA_ERROR:
                    progressBar.setVisibility(View.GONE);
                    tvStatus.setText(R.string.message_error_load_data);
                    break;
            }
        });

        productsViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            ProductsCategoriesAdapter adapter = new ProductsCategoriesAdapter(this::onCategorySelected);
            rvProductsCategories.setAdapter(adapter);

            adapter.updateCategories(categories);
        });
    }

    public void onCategorySelected(ProductsCategory category) {
        Intent intent = new Intent(requireContext(), ProductActivity.class);
        intent.putExtra(ProductActivity.PARAM_ID, category.getId());
        startActivity(intent);
    }
}
