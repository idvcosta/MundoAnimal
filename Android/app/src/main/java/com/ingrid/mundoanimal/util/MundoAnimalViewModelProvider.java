package com.ingrid.mundoanimal.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ingrid.mundoanimal.activities.category.CategoryDetailsViewModel;
import com.ingrid.mundoanimal.fragments.categorieslist.CategoriesListViewModel;
import com.ingrid.mundoanimal.fragments.home.HomeViewModel;
import com.ingrid.mundoanimal.repositories.MundoAnimalRepository;

public class MundoAnimalViewModelProvider implements ViewModelProvider.Factory {

    private Context context;
    private int categoryId;

    public MundoAnimalViewModelProvider(Context context) {
        this.context = context;
    }

    public MundoAnimalViewModelProvider(Context context, int categoryId) {
        this.context = context;
        this.categoryId = categoryId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        MundoAnimalRepository repository = new MundoAnimalRepository(context);

        if (modelClass == HomeViewModel.class) {
            return (T) new HomeViewModel(repository);
        } else if (modelClass == CategoriesListViewModel.class) {
            return (T) new CategoriesListViewModel(repository);
        } else if (modelClass == CategoryDetailsViewModel.class) {
            return (T) new CategoryDetailsViewModel(repository, categoryId);
        }

        return null;
    }
}
