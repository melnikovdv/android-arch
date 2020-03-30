package com.example.arch.screens.common.base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import com.example.arch.App;
import com.example.arch.di.AppComponent;
import com.example.arch.di.PresentationComponent;
import com.example.arch.di.PresentationModule;

public abstract class BaseActivity extends AppCompatActivity {

    private boolean isInjectorUsed;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @UiThread protected PresentationComponent getPresentationComponent(Bundle saveInstanceState) {
        if (isInjectorUsed) {
            throw new RuntimeException("there is no need to use injector more than once");
        }
        isInjectorUsed = true;
        return getAppComponent().newPresentationComponent(new PresentationModule(this, saveInstanceState));
    }

    private AppComponent getAppComponent() {
        return ((App) getApplication()).getAppComponent();
    }
}
