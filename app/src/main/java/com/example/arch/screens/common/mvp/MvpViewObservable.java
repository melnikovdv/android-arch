package com.example.arch.screens.common.mvp;

public interface MvpViewObservable<ListenerType> extends MvpView {

    void registerListener(ListenerType listener);

    void unregisterListener(ListenerType listener);

}
