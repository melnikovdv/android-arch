package com.example.arch.screens.blogitems;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.arch.blog.model.BlogItem;
import com.example.arch.screens.blogitems.row.BlogItemsRowMvpView;
import com.example.arch.screens.blogitems.row.BlogItemsRowMvpViewImpl;

import java.util.ArrayList;
import java.util.List;

public class BlogItemsAdapter extends RecyclerView.Adapter<BlogItemsAdapter.BlogItemViewHolder> implements
        BlogItemsRowMvpView.Listener {

    static class BlogItemViewHolder extends RecyclerView.ViewHolder {

        private BlogItemsRowMvpView blogItemsRowMvpView;

        public BlogItemViewHolder(BlogItemsRowMvpView blogItemsRowMvpView) {
            super(blogItemsRowMvpView.getRootView());
            this.blogItemsRowMvpView = blogItemsRowMvpView;
        }
    }

    private final LayoutInflater layoutInflater;
    private final BlogItemsRowMvpView.Listener listener;

    private final List<BlogItem> items = new ArrayList<>();

    public BlogItemsAdapter(LayoutInflater layoutInflater,
            BlogItemsRowMvpView.Listener listener) {
        this.layoutInflater = layoutInflater;
        this.listener = listener;
    }

    @NonNull @Override public BlogItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        BlogItemsRowMvpView blogItemsRowMvpView = new BlogItemsRowMvpViewImpl(layoutInflater, null, this);
        return new BlogItemViewHolder(blogItemsRowMvpView);
    }

    @Override public void onBindViewHolder(@NonNull BlogItemViewHolder blogItemViewHolder, int position) {
        BlogItem blogItem = items.get(position);
        blogItemViewHolder.blogItemsRowMvpView.bindData(blogItem);
    }

    @Override public int getItemCount() {
        return items.size();
    }

    @Override public void onBlogItemClicked(long blogItemId) {
        listener.onBlogItemClicked(blogItemId);
    }

    public void bindData(List<BlogItem> blogItems) {
        items.clear();
        items.addAll(blogItems);
        notifyDataSetChanged();
    }
}
