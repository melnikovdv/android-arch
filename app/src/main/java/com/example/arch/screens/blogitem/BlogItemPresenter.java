package com.example.arch.screens.blogitem;

import com.example.arch.blog.model.BlogItem;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.common.mvp.MvpPresenter;
import com.example.arch.screens.common.nav.BackPressDispatcher;
import com.example.arch.screens.common.nav.ScreenNavigator;
import com.example.arch.util.ThreadPoster;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class BlogItemPresenter implements MvpPresenter<BlogItemMvpView>,
        BlogItemMvpView.Listener {

    private final long id;
    private final ScreenNavigator screenNavigator;
    private final BackPressDispatcher backPressDispatcher;
    private final FindBlogItemService findBlogItemService;
    private final ThreadPoster mainThreadPoster;

    private BlogItemMvpView view;
    private Thread thread;

    public BlogItemPresenter(long id, ScreenNavigator screenNavigator, BackPressDispatcher backPressDispatcher,
            FindBlogItemService findBlogItemService, ThreadPoster mainThreadPoster) {
        this.id = id;
        this.screenNavigator = screenNavigator;
        this.backPressDispatcher = backPressDispatcher;
        this.findBlogItemService = findBlogItemService;
        this.mainThreadPoster = mainThreadPoster;
    }

    @Override public void bindView(BlogItemMvpView view) {
        this.view = view;
        view.showProgress();
        loadItem();
    }

    private void loadItem() {
        thread = new Thread(() -> {
            BlogItem blogItem = findBlogItemService.findById(id);
            if (!thread.isInterrupted()) {
                mainThreadPoster.post(() -> onItemLoaded(blogItem));
            }
        });
        thread.start();
    }

    @NotNull private void onItemLoaded(BlogItem blogItem) {
        // prepare to show
        view.hideProgress();
        view.bindData(blogItem);
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

    @Override public void onRefreshClicked() {
        Timber.d("onRefreshClicked");
        view.showProgress();
        thread = new Thread(() -> {
            BlogItem blogItem = findBlogItemService.refreshViewsAndVotes(id);
            mainThreadPoster.post(() -> onItemLoaded(blogItem));
        });
        thread.start();
    }

    @Override public void onBtnGoBackClicked() {
        screenNavigator.navigateUp();
    }

    @Override public boolean onBackPressed() {
        Timber.d("onBackPressed");
        screenNavigator.navigateUp();
        return true;
    }
}
