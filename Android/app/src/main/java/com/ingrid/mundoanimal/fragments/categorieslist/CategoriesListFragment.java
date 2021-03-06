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
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.activities.category.CategoryDetailsActivity;
import com.ingrid.mundoanimal.adapters.CategoriesAdapter;
import com.ingrid.mundoanimal.fragments.BaseFragment;
import com.ingrid.mundoanimal.model.Category;
import com.ingrid.mundoanimal.util.MundoAnimalViewModelProvider;

public class CategoriesListFragment extends BaseFragment<CategoriesListViewModel> {

    private RecyclerView rvProductsCategories;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products_categories, container, false);
        viewModel = new ViewModelProvider(this, new MundoAnimalViewModelProvider(requireContext()))
                .get(CategoriesListViewModel.class);

        initViews(view);
        initObserves();

        viewModel.loadData();

        return view;
    }

    protected void initViews(View view) {
        super.initViews(view);
        rvProductsCategories = view.findViewById(R.id.rvProductsCategories);
    }

    @Override
    protected void initObserves() {
        super.initObserves();

        viewModel.getCategories().observe(getViewLifecycleOwner(), categories -> {
            CategoriesAdapter adapter = new CategoriesAdapter(this::onCategorySelected);
            rvProductsCategories.setAdapter(adapter);

            adapter.updateCategories(categories);
        });
    }

    @Override
    public View getListView() {
        return rvProductsCategories;
    }

    public void onCategorySelected(Category category) {
        Intent intent = new Intent(requireContext(), CategoryDetailsActivity.class);
        intent.putExtra(CategoryDetailsActivity.PARAM_ID, category.getId());
        startActivity(intent);
    }
}
