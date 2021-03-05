package com.ingrid.mundoanimal.activities.category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.adapters.ProductsAdapter;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

public class CategoryDetailsActivity extends AppCompatActivity {

    public static final String PARAM_ID = "paramCategoryId";

    private SearchView seach;
    private ProgressBar progressBar;
    private TextView tvStatus;
    private RecyclerView rvItems;
    private CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        int categoryId = getIntent().getIntExtra(PARAM_ID, -1);

        initViews();
        initViewModel(categoryId);
        initObserves();
    }

    private void initViews() {
        seach = findViewById(R.id.search);
        progressBar = findViewById(R.id.progressBar);
        tvStatus = findViewById(R.id.tvStatus);
        rvItems = findViewById(R.id.rvItems);

    }

    private void initObserves() {
        categoryViewModel.getState().observe(this, state -> {
            switch (state) {
                case LOADING_INITIAL_DATA:
                case SEARCHING:
                    progressBar.setVisibility(View.VISIBLE);
                    tvStatus.setVisibility(View.VISIBLE);
                    rvItems.setVisibility(View.GONE);
                    break;
                case INITIAL_DATA_LOADED:
                case SHOW_SEARCH_RESULTS:
                    progressBar.setVisibility(View.GONE);
                    tvStatus.setVisibility(View.GONE);
                    rvItems.setVisibility(View.VISIBLE);
                    break;
                case LOAD_INITIAL_DATA_ERROR:
                    progressBar.setVisibility(View.GONE);
                    tvStatus.setText(R.string.message_error_load_data);
                    break;
            }
        });

        categoryViewModel.getProducts().observe(this, products -> {
            ProductsAdapter adapter = new ProductsAdapter();
            rvItems.setAdapter(adapter);

            adapter.updateItems(products);
        });
    }

    private void initViewModel(int categoryId) {
        categoryViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                MundoAnimalRepository repository = new MundoAnimalRepository(CategoryDetailsActivity.this);
                return (T) new CategoryViewModel(repository, categoryId);
            }
        }).get(CategoryViewModel.class);
    }
}