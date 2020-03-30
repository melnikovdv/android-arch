package com.example.arch.di;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import com.example.arch.R;
import com.example.arch.screens.common.nav.ScreenNavigator;
import com.ncapdevi.fragnav.FragNavController;
import dagger.Module;
import dagger.Provides;

@Module public class PresentationModule {

    private final AppCompatActivity activity;
    private final Bundle savedInstanceState;

    public PresentationModule(AppCompatActivity activity, Bundle savedInstanceState) {
        this.activity = activity;
        this.savedInstanceState = savedInstanceState;
    }

    @Provides @Presentation FragmentManager provideFragmentManager() {
        return activity.getSupportFragmentManager();
    }

    @Provides @Presentation LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(activity);
    }

    @Provides @Presentation Activity provideActivity() {
        return activity;
    }

    @Provides @Presentation Context provideContext(Activity activity) {
        return activity;
    }

    @Provides @Presentation FragNavController provideFragNavController(FragmentManager fragmentManager) {
        return new FragNavController(fragmentManager, R.id.container);
    }

    @Provides @Presentation ScreenNavigator provideScreenNavigator(FragNavController fragNavController) {
        return new ScreenNavigator(fragNavController, savedInstanceState);
    }
}
