package com.ingrid.mundoanimal.fragments.categorieslist;

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
import com.ingrid.mundoanimal.activities.category.CategoryDetailsActivity;
import com.ingrid.mundoanimal.adapters.CategoriesAdapter;
import com.ingrid.mundoanimal.model.Category;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

public class CategoriesListFragment extends Fragment {
    private SearchView seach;
    private ProgressBar progressBar;
    private TextView tvStatus;
    private RecyclerView rvProductsCategories;
    private CategoriesListViewModel categoriesListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_categories, container, false);
        categoriesListViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                MundoAnimalRepository repository = new MundoAnimalRepository(requireContext());
                return (T) new CategoriesListViewModel(repository);
            }
        }).get(CategoriesListViewModel.class);

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
        categoriesListViewModel.getState().observe(getViewLifecycleOwner(), state -> {
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

        categoriesListViewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            CategoriesAdapter adapter = new CategoriesAdapter(this::onCategorySelected);
            rvProductsCategories.setAdapter(adapter);

            adapter.updateCategories(categories);
        });
    }

    public void onCategorySelected(Category category) {
        Intent intent = new Intent(requireContext(), CategoryDetailsActivity.class);
        intent.putExtra(CategoryDetailsActivity.PARAM_ID, category.getId());
        startActivity(intent);
    }
}
