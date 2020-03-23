package com.example.arch.screens.common.nav;

public interface BackPressDispatcher {

    void registerListener(BackPressedListener listener);

    void unregisterListener(BackPressedListener listener);
}