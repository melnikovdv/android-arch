package com.example.arch;

import android.app.Application;
import com.example.arch.api.Api;
import com.example.arch.api.ApiImpl;
import com.example.arch.blog.repo.BlogItemRepo;
import com.example.arch.blog.repo.BlogItemRepoImpl;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.util.Generator;
import com.example.arch.util.MainThreadPoster;
import com.example.arch.util.ThreadPoster;
import timber.log.Timber;

public class App extends Application {

    private Api api;
    private Generator generator;
    private FindBlogItemService findBlogItemService;
    private BlogItemRepoImpl blogItemRepo;
    private MainThreadPoster mainThreadPoster;

    @Override public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Timber.d("onCreate");
    }

    private Api provideApi() {
        if (api == null) {
            api = new ApiImpl(provideGenerator());
        }
        return api;
    }

    private Generator provideGenerator() {
        if (generator == null) {
            generator = new Generator();
        }
        return generator;
    }

    public FindBlogItemService provideFindBlogItemService() {
        if (findBlogItemService == null) {
            findBlogItemService = new FindBlogItemService(provideBlogItemRepo(), provideApi());
        }
        return findBlogItemService;
    }

    private BlogItemRepo provideBlogItemRepo() {
        if (blogItemRepo == null) {
            blogItemRepo = new BlogItemRepoImpl();
        }
        return blogItemRepo;
    }

    public ThreadPoster provideMainThreadPoster() {
        if (mainThreadPoster == null) {
            mainThreadPoster = new MainThreadPoster();
        }
        return mainThreadPoster;
    }
}
