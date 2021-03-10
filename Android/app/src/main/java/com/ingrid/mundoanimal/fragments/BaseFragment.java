package com.ingrid.mundoanimal.fragments;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.util.BaseViewModel;

public abstract class BaseFragment<ViewModelType extends BaseViewModel> extends Fragment {

    protected ViewModelType viewModel;
    protected SearchView search;
    protected ProgressBar progressBar;
    protected TextView tvStatus;

    protected void initViews(View view) {
        search = view.findViewById(R.id.search);
        progressBar = view.findViewById(R.id.progressBar);
        tvStatus = view.findViewById(R.id.tvStatus);
    }

    protected void initObserves() {
        viewModel.getState().observe(getViewLifecycleOwner(), state -> {
            switch (state) {
                case LOADING_INITIAL_DATA:
                case SEARCHING:
                    showLoading();
                    break;
                case INITIAL_DATA_LOADED:
                case SHOW_SEARCH_RESULTS:
                    showLoaded();
                    break;
                case LOAD_INITIAL_DATA_ERROR:
                    progressBar.setVisibility(View.GONE);
                    tvStatus.setText(R.string.message_error_load_data);
                    break;
            }
        });
    }

    protected void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        tvStatus.setVisibility(View.VISIBLE);
        getListView().setVisibility(View.GONE);
    }

    protected void showLoaded() {
        progressBar.setVisibility(View.GONE);
        tvStatus.setVisibility(View.GONE);
        getListView().setVisibility(View.VISIBLE);
    }

    public abstract View getListView();
}
