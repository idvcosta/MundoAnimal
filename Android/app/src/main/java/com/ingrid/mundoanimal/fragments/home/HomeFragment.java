package com.ingrid.mundoanimal.fragments.home;

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
import androidx.viewpager2.widget.ViewPager2;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.adapters.HighlightsAdapter;
import com.ingrid.mundoanimal.adapters.ProductsAdapter;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

public class HomeFragment extends Fragment {
    private SearchView seach;
    private ProgressBar progressBar;
    private TextView tvStatus;
    private RecyclerView rvItems;
    private HomeViewModel homeViewModel;
    private ViewPager2 vpHighlights;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel = new ViewModelProvider(this, new ViewModelProvider.Factory() {
            @NonNull
            @Override
            public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                MundoAnimalRepository repository = new MundoAnimalRepository(requireContext());
                return (T) new HomeViewModel(repository);
            }
        }).get(HomeViewModel.class);

        initViews(view);
        initObserves();

        return view;
    }

    private void initViews(View view) {
        seach = view.findViewById(R.id.search);
        progressBar = view.findViewById(R.id.progressBar);
        tvStatus = view.findViewById(R.id.tvStatus);
        rvItems = view.findViewById(R.id.rvItems);
        vpHighlights = view.findViewById(R.id.vpHighlights);

    }

    private void initObserves() {
        homeViewModel.getState().observe(getViewLifecycleOwner(), state -> {
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

        homeViewModel.getMostWanted().observe(getViewLifecycleOwner(), homeItems -> {
            ProductsAdapter adapter = new ProductsAdapter();
            rvItems.setAdapter(adapter);

            adapter.updateItems(homeItems);
        });

        homeViewModel.getHightlight().observe(getViewLifecycleOwner(), highlightItems -> {
            HighlightsAdapter adapter = new HighlightsAdapter();
            vpHighlights.setAdapter(adapter);

            adapter.updateHighlights(highlightItems);
        });
    }
}
