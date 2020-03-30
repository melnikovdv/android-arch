package com.example.arch;

import android.app.Application;
import com.example.arch.di.AppComponent;
import com.example.arch.di.AppModule;
import com.example.arch.di.DaggerAppComponent;
import timber.log.Timber;

public class App extends Application {

    private AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
        Timber.d("onCreate");
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
