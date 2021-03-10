package com.ingrid.mundoanimal.fragments.services;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.adapters.ServicesAdapter;
import com.ingrid.mundoanimal.fragments.BaseFragment;
import com.ingrid.mundoanimal.fragments.home.HomeViewModel;
import com.ingrid.mundoanimal.model.Services;
import com.ingrid.mundoanimal.util.BaseViewModel;
import com.ingrid.mundoanimal.util.MundoAnimalViewModelProvider;

public class ServicesFragment extends BaseFragment<ServicesViewModel> {
    private RecyclerView rvItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_services, container, false);
        viewModel = new ViewModelProvider(this, new MundoAnimalViewModelProvider(requireContext()))
                .get(ServicesViewModel.class);

        initViews(view);
        initObserves();

        viewModel.loadData();

        return view;
    }

    @Override
    protected void initViews(View view) {
        super.initViews(view);
        rvItems = view.findViewById(R.id.rvItems);
    }

    @Override
    protected void initObserves() {
        super.initObserves();

        viewModel.getServices().observe(getViewLifecycleOwner(), services -> {
            ServicesAdapter servicesAdapter = new ServicesAdapter();
            rvItems.setAdapter(servicesAdapter);

            servicesAdapter.updateItems(services);
        });
    }

    @Override
    public View getListView() {
        return rvItems;
    }
}

