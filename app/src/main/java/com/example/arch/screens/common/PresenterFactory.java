package com.example.arch.screens.common;

import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.di.Presentation;
import com.example.arch.screens.blogitem.BlogItemPresenter;
import com.example.arch.screens.blogitems.BlogItemsPresenter;
import com.example.arch.screens.common.nav.BackPressDispatcher;
import com.example.arch.screens.common.nav.ScreenNavigator;
import com.example.arch.util.ThreadPoster;

import javax.inject.Inject;
import javax.inject.Named;

@Presentation
public class PresenterFactory {

    private final ScreenNavigator screenNavigator;
    private final FindBlogItemService findBlogItemService;
    private final ThreadPoster mainThreadPoster;

    @Inject public PresenterFactory(ScreenNavigator screenNavigator, FindBlogItemService findBlogItemService,
            @Named("mainThread") ThreadPoster mainThreadPoster) {
        this.screenNavigator = screenNavigator;
        this.findBlogItemService = findBlogItemService;
        this.mainThreadPoster = mainThreadPoster;
    }

    public BlogItemPresenter getBlogItemPresenter(long id, BackPressDispatcher backPressDispatcher) {
        return new BlogItemPresenter(
                id,
                screenNavigator,
                backPressDispatcher,
                findBlogItemService,
                mainThreadPoster
        );
    }

    public BlogItemsPresenter getBlogItemsPresenter(BackPressDispatcher backPressDispatcher) {
        return new BlogItemsPresenter(
                screenNavigator,
                backPressDispatcher,
                findBlogItemService,
                mainThreadPoster
        );
    }
}
