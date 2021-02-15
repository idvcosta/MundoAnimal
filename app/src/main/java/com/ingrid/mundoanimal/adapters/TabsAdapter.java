package com.ingrid.mundoanimal.adapters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ingrid.mundoanimal.fragments.HomeFragment;
import com.ingrid.mundoanimal.fragments.ProductsFragment;
import com.ingrid.mundoanimal.fragments.ServicesFragment;

public class TabsAdapter extends FragmentStateAdapter {
    public TabsAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new HomeFragment();
        } else if (position == 1) {
            return new ProductsFragment();
        } else {
            return new ServicesFragment();
        }


//        // Return a NEW fragment instance in createFragment(int)
//        Fragment fragment = new DemoObjectFragment();
//        Bundle args = new Bundle();
//        // Our object is just an integer :-P
//        args.putInt(DemoObjectFragment.ARG_OBJECT, position + 1);
//        fragment.setArguments(args);
//        return fragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
