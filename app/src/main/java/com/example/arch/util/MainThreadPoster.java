package com.example.arch.util;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton public class MainThreadPoster implements ThreadPoster {

    Handler mainHandler = new Handler(Looper.getMainLooper());

    @Inject public MainThreadPoster() {
    }

    @Override public void post(Runnable runnable) {
        mainHandler.post(runnable);
    }
}
