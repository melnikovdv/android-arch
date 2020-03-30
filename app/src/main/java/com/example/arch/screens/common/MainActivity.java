package com.example.arch.screens.common;

import android.os.Bundle;
import com.example.arch.R;
import com.example.arch.di.PresentationComponent;
import com.example.arch.screens.common.base.BaseActivity;
import com.example.arch.screens.common.nav.BackPressDispatcher;
import com.example.arch.screens.common.nav.BackPressedListener;
import com.example.arch.screens.common.nav.ScreenNavigator;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends BaseActivity implements BackPressDispatcher {

    private final Set<BackPressedListener> backPressedListeners = new HashSet<>();

    @Inject ScreenNavigator screenNavigator;
    private PresentationComponent presentationComponent;
    private Bundle savedInstanceState;

    @Override protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
        setContentView(R.layout.main_activity);
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        screenNavigator.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        boolean isBackPressConsumedByAnyListener = false;
        for (BackPressedListener listener : backPressedListeners) {
            if (listener.onBackPressed()) {
                isBackPressConsumedByAnyListener = true;
            }
        }
        if (!isBackPressConsumedByAnyListener) {
            super.onBackPressed();
        }
    }

    @Override public void registerListener(BackPressedListener listener) {
        backPressedListeners.add(listener);
    }

    @Override public void unregisterListener(BackPressedListener listener) {
        backPressedListeners.remove(listener);
    }

    public PresentationComponent getPresentationComponent() {
        if (presentationComponent == null) {
            presentationComponent = getPresentationComponent(savedInstanceState);
        }
        return presentationComponent;
    }
}
