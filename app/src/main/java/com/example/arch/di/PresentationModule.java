package com.example.arch.di;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.example.arch.R;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.common.MvpViewFactory;
import com.example.arch.screens.common.PresenterFactory;
import com.example.arch.screens.common.ViewModelFactory;
import com.example.arch.screens.common.nav.ScreenNavigator;
import com.example.arch.util.ThreadPoster;
import com.ncapdevi.fragnav.FragNavController;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module public class PresentationModule {

    private final AppCompatActivity activity;
    private final Bundle savedInstanceState;

    public PresentationModule(AppCompatActivity activity, Bundle savedInstanceState) {
        this.activity = activity;
        this.savedInstanceState = savedInstanceState;
    }

    @Provides FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides @Presentation LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(activity);
    }

    @Provides Activity provideActivity() {
        return activity;
    }

    @Provides Context provideContext(Activity activity) {
        return activity;
    }

    @Provides @Presentation ScreenNavigator provideScreenNavigator(FragNavController fragNavController) {
        return new ScreenNavigator(fragNavController, savedInstanceState);
    }

    @Provides @Presentation FragNavController provideFragNavController(FragmentManager fragmentManager) {
        return new FragNavController(fragmentManager, R.id.container);
    }

    @Provides @Presentation MvpViewFactory provideMvpViewFactory(LayoutInflater layoutInflater) {
        return new MvpViewFactory(layoutInflater);
    }

    @Provides @Presentation PresenterFactory providePresenterFactory(ScreenNavigator screenNavigator,
            FindBlogItemService findBlogItemService, @Named("mainThread") ThreadPoster threadPoster) {
        return new PresenterFactory(screenNavigator, findBlogItemService, threadPoster);
    }

    @Provides @Presentation ViewModelFactory provideViewModelFactory(ScreenNavigator screenNavigator,
            FindBlogItemService findBlogItemService) {
        return new ViewModelFactory(screenNavigator, findBlogItemService);
    }
}
