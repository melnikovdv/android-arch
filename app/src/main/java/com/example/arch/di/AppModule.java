package com.example.arch.di;

import android.app.Application;
import com.example.arch.api.Api;
import com.example.arch.api.ApiImpl;
import com.example.arch.blog.repo.BlogItemRepo;
import com.example.arch.blog.repo.BlogItemRepoImpl;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.util.Generator;
import com.example.arch.util.MainThreadPoster;
import com.example.arch.util.ThreadPoster;
import dagger.Module;
import dagger.Provides;

import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton Api getApi(Generator generator) {
        return new ApiImpl(generator);
    }

    @Provides @Singleton Generator provideGenerator() {
        return new Generator();
    }

    @Provides @Singleton FindBlogItemService provideFindBlogItemService(BlogItemRepo blogItemRepo, Api api) {
        return new FindBlogItemService(blogItemRepo, api);
    }

    @Provides @Singleton BlogItemRepo provideBlogItemRepo() {
        return new BlogItemRepoImpl();
    }

    @Provides @Singleton @Named("mainThread") ThreadPoster provideMainThreadPoster() {
        return new MainThreadPoster();
    }
}
