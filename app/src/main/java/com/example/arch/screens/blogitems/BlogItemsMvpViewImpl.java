package com.example.arch.screens.blogitems;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.arch.R;
import com.example.arch.blog.model.BlogItem;
import com.example.arch.screens.blogitems.row.BlogItemsRowMvpView;
import com.example.arch.screens.common.mvp.MvpViewObservableBase;

import java.util.List;

public class BlogItemsMvpViewImpl extends MvpViewObservableBase<BlogItemsMvpView.Listener>
        implements BlogItemsMvpView, BlogItemsRowMvpView.Listener {

    private final RecyclerView rvBlogItems;
    private final BlogItemsAdapter blogItemsAdapter;

    public BlogItemsMvpViewImpl(LayoutInflater layoutInflater, ViewGroup parent, Context context) {
        setRootView(layoutInflater.inflate(R.layout.blog_items_fragment, parent, false));

        blogItemsAdapter = new BlogItemsAdapter(layoutInflater, this);
        rvBlogItems = findViewById(R.id.blog_items_fragment__rvBlogItems);
        rvBlogItems.setLayoutManager(new LinearLayoutManager(context));
        rvBlogItems.setAdapter(blogItemsAdapter);
    }

    @Override public void bindData(List<BlogItem> blogItems) {
        blogItemsAdapter.bindData(blogItems);
    }

    @Override public void onBlogItemClicked(long blogItemId) {
        for (Listener listener : getListeners()) {
            listener.onBlogItemClicked(blogItemId);
        }
    }
}
