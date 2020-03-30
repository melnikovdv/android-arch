package com.example.arch.di;

import android.app.Application;
import com.example.arch.api.Api;
import com.example.arch.api.ApiImpl;
import com.example.arch.blog.repo.BlogItemRepo;
import com.example.arch.blog.repo.BlogItemRepoImpl;
import com.example.arch.util.MainThreadPoster;
import com.example.arch.util.ThreadPoster;
import dagger.Binds;
import dagger.Module;

import javax.inject.Named;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Module interface BindsModule {
        @Binds @Named("mainThread") ThreadPoster getMainThreadPoster(MainThreadPoster mainThreadPoster);

        @Binds Api getApi(ApiImpl apiImpl);

        @Binds BlogItemRepo getBlogItemRepo(BlogItemRepoImpl blogItemRepoImpl);
    }
}
