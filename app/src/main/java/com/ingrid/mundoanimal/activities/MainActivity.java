package com.ingrid.mundoanimal.activities;

import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.ingrid.mundoanimal.R;
import com.ingrid.mundoanimal.adapters.TabsAdapter;

public class MainActivity extends FragmentActivity {

    private ViewPager2 viewPager;
    private TabsAdapter tabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        initTabs();
        initNavController();
    }

    private void initTabs() {
        tabsAdapter = new TabsAdapter(this);
        viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(tabsAdapter);


        TabLayout tabLayout = findViewById(R.id.tab_layout);
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Início");
                        tab.setIcon(R.drawable.home);
                    } else if (position == 1) {
                        tab.setText("Produtos");
                        tab.setIcon(R.drawable.products);
                    } else {
                        tab.setText("Serviços");
                        tab.setIcon(R.drawable.service1);
                    }
                }
        ).attach();
    }

    private void initNavController() {

    }
}