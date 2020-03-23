package com.example.arch.screens.blogitems;

import com.example.arch.blog.model.BlogItem;
import com.example.arch.screens.common.mvp.MvpViewObservable;
import com.example.arch.screens.common.nav.BackPressedListener;

import java.util.List;

public interface BlogItemsMvpView extends MvpViewObservable<BlogItemsMvpView.Listener> {

    interface Listener extends BackPressedListener {

        void onBlogItemClicked(long blogItemId);

    }

    void bindData(List<BlogItem> blogItems);
}
