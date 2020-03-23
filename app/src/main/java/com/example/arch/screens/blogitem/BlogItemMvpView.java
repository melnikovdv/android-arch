package com.example.arch.screens.blogitem;

import com.example.arch.blog.model.BlogItem;
import com.example.arch.screens.common.mvp.MvpViewObservable;
import com.example.arch.screens.common.nav.BackPressedListener;

public interface BlogItemMvpView extends MvpViewObservable<BlogItemMvpView.Listener> {

    interface Listener extends BackPressedListener {

        void onRefreshClicked();

        void onBtnGoBackClicked();
    }

    void showProgress();

    void hideProgress();

    void bindData(BlogItem blogItem);
}
