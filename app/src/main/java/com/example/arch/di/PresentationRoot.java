package com.example.arch.di;

import android.os.Bundle;
import android.view.LayoutInflater;
import androidx.appcompat.app.AppCompatActivity;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.common.MvpViewFactory;
import com.example.arch.screens.common.PresenterFactory;
import com.example.arch.screens.common.ViewModelFactory;
import com.example.arch.screens.common.nav.ScreenNavigator;
import com.example.arch.util.ThreadPoster;

public class PresentationRoot {

    private final AppCompatActivity context;
    private final CompositionRoot compositionRoot;

    private ScreenNavigator screenNavigator;
    private MvpViewFactory mvpViewFactory;
    private LayoutInflater layoutInflater;
    private PresenterFactory presenterFactory;
    private ViewModelFactory viewModelFactory;

    public PresentationRoot(AppCompatActivity context, CompositionRoot compositionRoot,
            Bundle savedInstanceState) {
        this.context = context;
        this.compositionRoot = compositionRoot;
        screenNavigator = new ScreenNavigator(context.getSupportFragmentManager(), savedInstanceState);
    }

    public ScreenNavigator getScreenNavigator() {
        return screenNavigator;
    }

    public FindBlogItemService getFindBlogItemService() {
        return compositionRoot.provideFindBlogItemService();
    }

    public ThreadPoster getMainThreadPoster() {
        return compositionRoot.provideMainThreadPoster();
    }

    public MvpViewFactory getMvpViewFactory() {
        if (mvpViewFactory == null) {
            mvpViewFactory = new MvpViewFactory(getLayoutInflater());
        }
        return mvpViewFactory;
    }

    public LayoutInflater getLayoutInflater() {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(context);
        }
        return layoutInflater;
    }

    public PresenterFactory getPresenterFactory() {
        if (presenterFactory == null) {
            presenterFactory = new PresenterFactory(
                    getScreenNavigator(),
                    getFindBlogItemService(),
                    getMainThreadPoster());
        }
        return presenterFactory;
    }

    public ViewModelFactory provideViewModelFactory() {
        if (viewModelFactory == null) {
            viewModelFactory = new ViewModelFactory(getScreenNavigator(), getFindBlogItemService());
        }
        return viewModelFactory;
    }
}
