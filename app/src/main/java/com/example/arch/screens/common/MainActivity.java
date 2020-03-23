package com.example.arch.screens.common;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.arch.R;
import com.example.arch.screens.common.nav.BackPressDispatcher;
import com.example.arch.screens.common.nav.BackPressedListener;
import com.example.arch.screens.common.nav.ScreenNavigator;

import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements BackPressDispatcher {

    private final Set<BackPressedListener> backPressedListeners = new HashSet<>();

    private ScreenNavigator screenNavigator;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        screenNavigator = new ScreenNavigator(getSupportFragmentManager(), savedInstanceState);

    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        screenNavigator.onSaveInstanceState(outState);
    }

    public ScreenNavigator getScreenNavigator() {
        return screenNavigator;
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
}
