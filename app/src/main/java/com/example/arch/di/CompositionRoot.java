package com.example.arch.di;

import android.content.Context;
import com.example.arch.api.Api;
import com.example.arch.api.ApiImpl;
import com.example.arch.blog.repo.BlogItemRepo;
import com.example.arch.blog.repo.BlogItemRepoImpl;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.util.Generator;
import com.example.arch.util.MainThreadPoster;
import com.example.arch.util.ThreadPoster;

public class CompositionRoot {

    private final Context context;

    private Api api;
    private Generator generator;
    private FindBlogItemService findBlogItemService;
    private BlogItemRepoImpl blogItemRepo;
    private MainThreadPoster mainThreadPoster;

    public CompositionRoot(Context context) {
        this.context = context;
    }

    private Api getApi() {
        if (api == null) {
            api = new ApiImpl(getGenerator());
        }
        return api;
    }

    private Generator getGenerator() {
        if (generator == null) {
            generator = new Generator();
        }
        return generator;
    }

    public FindBlogItemService getFindBlogItemService() {
        if (findBlogItemService == null) {
            findBlogItemService = new FindBlogItemService(getBlogItemRepo(), getApi());
        }
        return findBlogItemService;
    }

    private BlogItemRepo getBlogItemRepo() {
        if (blogItemRepo == null) {
            blogItemRepo = new BlogItemRepoImpl();
        }
        return blogItemRepo;
    }

    public ThreadPoster getMainThreadPoster() {
        if (mainThreadPoster == null) {
            mainThreadPoster = new MainThreadPoster();
        }
        return mainThreadPoster;
    }
}
