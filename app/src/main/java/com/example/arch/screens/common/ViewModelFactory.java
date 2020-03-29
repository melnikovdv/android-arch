package com.example.arch.screens.common;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.blogitem2.BlogItemViewModel;
import com.example.arch.screens.common.nav.ScreenNavigator;

public class ViewModelFactory {

    private final ScreenNavigator screenNavigator;
    private final FindBlogItemService findBlogItemService;

    public ViewModelFactory(ScreenNavigator screenNavigator, FindBlogItemService findBlogItemService) {
        this.screenNavigator = screenNavigator;
        this.findBlogItemService = findBlogItemService;
    }

    public ViewModelProvider getViewModelProvider(long id, Fragment fragment) {
        return new ViewModelProvider(fragment, createViewModelProviderFactory(id));
    }

    private ViewModelProvider.Factory createViewModelProviderFactory(long id) {
        return new ViewModelProvider.Factory() {
            @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass == BlogItemViewModel.class) {
                    BlogItemViewModel blogItemViewModel = new BlogItemViewModel(id, findBlogItemService,
                            screenNavigator);
                    return (T) blogItemViewModel;
                }
                throw new IllegalStateException("Unknown ViewModel");
            }
        };
    }
}
