package com.ingrid.mundoanimal.fragments.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.adapters.TabsAdapter;

public class MainFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    private void init(View view) {
        TabsAdapter tabsAdapter = new TabsAdapter(requireActivity());
        ViewPager2 viewPager = view.findViewById(R.id.pager);


        //////////////////////////


        ///////////////////////////////////
//        viewPager.setNestedScrollingEnabled(true);
        viewPager.setAdapter(tabsAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText(R.string.tab_text_home);
                        tab.setIcon(R.drawable.home);
                    } else if (position == 1) {
                        tab.setText(R.string.tab_text_products);
                        tab.setIcon(R.drawable.products);
                    } else {
                        tab.setText(R.string.tab_text_services);
                        tab.setIcon(R.drawable.service1);
                    }
                }
        ).attach();
    }
}
