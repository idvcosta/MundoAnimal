package com.ingrid.mundoanimal.fragments.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.adapters.HighlightsAdapter;
import com.ingrid.mundoanimal.adapters.ProductsAdapter;
import com.ingrid.mundoanimal.fragments.BaseFragment;
import com.ingrid.mundoanimal.util.MundoAnimalViewModelProvider;

public class HomeFragment extends BaseFragment<HomeViewModel> {

    private RecyclerView rvItems;
    private ViewPager2 vpHighlights;
    private TextView tvMostWantedHeader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        viewModel = new ViewModelProvider(this, new MundoAnimalViewModelProvider(requireContext()))
                .get(HomeViewModel.class);

        initViews(view);
        initObserves();

        viewModel.loadData();

        return view;
    }

    protected void initViews(View view) {
        super.initViews(view);
        rvItems = view.findViewById(R.id.rvItems);
        vpHighlights = view.findViewById(R.id.vpHighlights);
        tvMostWantedHeader = view.findViewById(R.id.tvMostWantedHeader);
    }

    @Override
    protected void showLoading() {
        super.showLoading();
        tvMostWantedHeader.setVisibility(View.GONE);
    }

    @Override
    protected void showLoaded() {
        super.showLoaded();
        tvMostWantedHeader.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initObserves() {
        super.initObserves();

        viewModel.getMostWanted().observe(getViewLifecycleOwner(), homeItems -> {
            ProductsAdapter adapter = new ProductsAdapter();
            rvItems.setAdapter(adapter);

            adapter.updateItems(homeItems);
        });

        viewModel.getHightlight().observe(getViewLifecycleOwner(), highlightItems -> {
            HighlightsAdapter adapter = new HighlightsAdapter();
            vpHighlights.setAdapter(adapter);

            adapter.updateHighlights(highlightItems);
        });
    }

    @Override
    public View getListView() {
        return rvItems;
    }
}
