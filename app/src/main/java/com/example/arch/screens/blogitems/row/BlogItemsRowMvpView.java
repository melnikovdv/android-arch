package com.example.arch.screens.blogitems.row;

import com.example.arch.blog.model.BlogItem;
import com.example.arch.screens.common.mvp.MvpView;

import java.util.List;

public interface BlogItemsRowMvpView extends MvpView {

    interface Listener {

        void onBlogItemClicked(long blogItemId);

    }

    void bindData(BlogItem blogItem);
}
