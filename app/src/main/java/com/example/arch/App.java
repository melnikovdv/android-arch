package com.example.arch;

import android.app.Application;
import com.example.arch.di.CompositionRoot;
import timber.log.Timber;

public class App extends Application {

    private CompositionRoot compositionRoot;

    @Override public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Timber.d("onCreate");
        compositionRoot = new CompositionRoot(this);
    }

    public CompositionRoot getCompositionRoot() {
        return compositionRoot;
    }
}
