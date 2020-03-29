package com.example.arch.screens.blogitem2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.arch.blog.model.BlogItem;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.common.nav.BackPressedListener;
import com.example.arch.screens.common.nav.ScreenNavigator;
import timber.log.Timber;

public class BlogItemViewModel extends ViewModel implements BackPressedListener {

    private final long itemId;
    private final FindBlogItemService findBlogItemService;

    private ScreenNavigator screenNavigator;
    private BlogItem blogItem;
    private MutableLiveData<BlogItem> blogItemLiveData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loadingLiveData = new MutableLiveData<>();

    private Thread thread;

    public BlogItemViewModel(long itemId, FindBlogItemService findBlogItemService, ScreenNavigator screenNavigator) {
        this.itemId = itemId;
        this.findBlogItemService = findBlogItemService;
        this.screenNavigator = screenNavigator;
        loadingLiveData.setValue(true);
    }

    public void updateScreenNavigator(ScreenNavigator screenNavigator) {
        this.screenNavigator = screenNavigator;
    }

    public MutableLiveData<BlogItem> getBlogItemLiveData() {
        return blogItemLiveData;
    }

    public LiveData<Boolean> getLoadingLiveData() {
        return loadingLiveData;
    }

    public void loadItem() {
        if (blogItem == null) {
            thread = new Thread(() -> {
                Timber.d("loadItem");
                blogItem = findBlogItemService.findById(itemId);
                if (!thread.isInterrupted()) {
                    postLiveData(blogItem);
                }
            });
            thread.start();
        }
    }

    private void postLiveData(BlogItem blogItem) {
        Timber.d("postLiveData");
        blogItemLiveData.postValue(blogItem);
        loadingLiveData.postValue(false);
    }

    @Override protected void onCleared() {
        Timber.d("onCleared");
        thread.interrupt();
        thread = null;
    }

    public void onGoBackClicked() {
        internalGoBack();
    }

    private void internalGoBack() {
        screenNavigator.navigateUp();
    }

    public void onRefreshClicked() {
        Timber.d("onRefreshClicked");
        loadingLiveData.postValue(true);
        thread = new Thread(() -> {
            BlogItem refreshedBlogItem = findBlogItemService.refreshViewsAndVotes(blogItem.getId());
            if (!thread.isInterrupted()) {
                postLiveData(refreshedBlogItem);
            }
        });
        thread.start();
    }

    @Override public boolean onBackPressed() {
        internalGoBack();
        return true;
    }
}
