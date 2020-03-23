package com.example.arch.screens.blogitems.row;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.arch.R;
import com.example.arch.blog.model.BlogItem;
import com.example.arch.screens.common.mvp.MvpViewBase;

public class BlogItemsRowMvpViewImpl extends MvpViewBase
        implements BlogItemsRowMvpView {

    private final Listener listener;

    private final TextView tvTitle;
    private final TextView tvViewCount;
    private final TextView tvUpVotes;
    private final TextView tvDownVotes;

    private BlogItem blogItem;

    public BlogItemsRowMvpViewImpl(LayoutInflater layoutInflater, ViewGroup parent,
            BlogItemsRowMvpView.Listener listener) {
        this.listener = listener;
        setRootView(layoutInflater.inflate(R.layout.blog_items_row, parent, false));

        tvTitle = findViewById(R.id.blog_item_fragment__tvTitle);
        tvViewCount = findViewById(R.id.blog_item_fragment__tvViewCount);
        tvUpVotes = findViewById(R.id.blog_item_fragment__tvUpVotes);
        tvDownVotes = findViewById(R.id.blog_item_fragment__tvDownVotes);

        getRootView().setOnClickListener(view -> onBlogItemClicked());
    }

    @Override public void bindData(BlogItem blogItem) {
        this.blogItem = blogItem;
        tvTitle.setText(blogItem.getTitle());
        tvViewCount.setText(String.valueOf(blogItem.getViewCount()));
        tvUpVotes.setText(String.valueOf(blogItem.getUpVotes()));
        tvDownVotes.setText(String.valueOf(blogItem.getDownVotes()));
    }

    public void onBlogItemClicked() {
        listener.onBlogItemClicked(blogItem.getId());
    }
}
