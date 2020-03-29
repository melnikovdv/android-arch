package com.example.arch.screens.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.example.arch.screens.blogitem.BlogItemMvpView;
import com.example.arch.screens.blogitem.BlogItemMvpViewImpl;
import com.example.arch.screens.blogitems.BlogItemsAdapter;
import com.example.arch.screens.blogitems.BlogItemsMvpView;
import com.example.arch.screens.blogitems.BlogItemsMvpViewImpl;
import com.example.arch.screens.blogitems.row.BlogItemsRowMvpView;

public class MvpViewFactory {

    private final LayoutInflater layoutInflater;

    public MvpViewFactory(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    public BlogItemMvpView getBlogItemMvpView(ViewGroup parent) {
        return new BlogItemMvpViewImpl(layoutInflater, parent);
    }

    public BlogItemsAdapter getBlogItemsAdapter(BlogItemsRowMvpView.Listener listener) {
        return new BlogItemsAdapter(layoutInflater, listener);
    }

    public BlogItemsMvpView getBlogItemsView(ViewGroup container, Context context) {
        return new BlogItemsMvpViewImpl(layoutInflater, container, context, this);
    }
}
