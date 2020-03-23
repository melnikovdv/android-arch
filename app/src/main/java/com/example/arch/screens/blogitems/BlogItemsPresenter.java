package com.example.arch.screens.blogitems;

import com.example.arch.blog.model.BlogItem;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.common.mvp.MvpPresenter;
import com.example.arch.screens.common.nav.BackPressDispatcher;
import com.example.arch.screens.common.nav.ScreenNavigator;
import com.example.arch.util.ThreadPoster;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

import java.util.List;

public class BlogItemsPresenter implements MvpPresenter<BlogItemsMvpView>,
        BlogItemsMvpView.Listener {

    private final ScreenNavigator screenNavigator;
    private final BackPressDispatcher backPressDispatcher;
    private final FindBlogItemService findBlogItemService;
    private final ThreadPoster mainThreadPoster;

    private BlogItemsMvpView view;
    private Thread thread;

    public BlogItemsPresenter(ScreenNavigator screenNavigator, BackPressDispatcher backPressDispatcher,
            FindBlogItemService findBlogItemService, ThreadPoster mainThreadPoster) {
        this.screenNavigator = screenNavigator;
        this.backPressDispatcher = backPressDispatcher;
        this.findBlogItemService = findBlogItemService;
        this.mainThreadPoster = mainThreadPoster;
    }

    @Override public void bindView(BlogItemsMvpView view) {
        this.view = view;
        loadItems();
    }

    private void loadItems() {
        thread = new Thread(() -> {
            List<BlogItem> blogItems = findBlogItemService.findAll();
            if (!thread.isInterrupted()) {
                mainThreadPoster.post(() -> onItemsLoaded(blogItems));
            }
        });
        thread.start();
    }

    @NotNull private void onItemsLoaded(List<BlogItem> blogItems) {
        // prepare to show
        view.bindData(blogItems);
    }

    @Override public void onStart() {
        view.registerListener(this);
        backPressDispatcher.registerListener(this);
    }

    @Override public void onStop() {
        view.unregisterListener(this);
        backPressDispatcher.unregisterListener(this);
    }

    @Override public void onDestroy() {
        // dispose all requests
        thread.interrupt();
        thread = null;
        view = null;
    }

    @Override public boolean onBackPressed() {
        Timber.d("onBackPressed");
        screenNavigator.navigateUp();
        return true;
    }

    @Override public void onBlogItemClicked(long blogItemId) {
        Timber.d("onBlogItemClicked %d", blogItemId);
    }
}
