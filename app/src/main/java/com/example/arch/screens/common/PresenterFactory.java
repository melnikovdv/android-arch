package com.example.arch.screens.common;

import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.blogitem.BlogItemPresenter;
import com.example.arch.screens.blogitems.BlogItemsPresenter;
import com.example.arch.screens.common.nav.BackPressDispatcher;
import com.example.arch.screens.common.nav.ScreenNavigator;
import com.example.arch.util.ThreadPoster;

public class PresenterFactory {

    private final ScreenNavigator screenNavigator;
    private final FindBlogItemService findBlogItemService;
    private final ThreadPoster mainThreadPoster;

    public PresenterFactory(ScreenNavigator screenNavigator, FindBlogItemService findBlogItemService,
            ThreadPoster mainThreadPoster) {
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
