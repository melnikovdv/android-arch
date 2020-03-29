package com.example.arch.screens.blogitem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.arch.R;
import com.example.arch.blog.model.BlogItem;
import com.example.arch.screens.common.mvp.MvpViewObservableBase;

public class BlogItemMvpViewImpl extends MvpViewObservableBase<BlogItemMvpView.Listener>
        implements BlogItemMvpView {

    private final ProgressBar progress;
    private final LinearLayout llContent;
    private final TextView tvTitle;
    private final TextView tvText;
    private final TextView tvViewCount;
    private final TextView tvUpVotes;
    private final TextView tvDownVotes;

    public BlogItemMvpViewImpl(LayoutInflater layoutInflater, ViewGroup parent) {
        setRootView(layoutInflater.inflate(R.layout.blog_item_fragment, parent, false));
        progress = findViewById(R.id.blog_item_fragment__progress);
        llContent = findViewById(R.id.blog_item_fragment__llContent);
        tvTitle = findViewById(R.id.blog_item_fragment__tvTitle);
        tvText = findViewById(R.id.blog_item_fragment__tvText);
        tvViewCount = findViewById(R.id.blog_item_fragment__tvViewCount);
        tvUpVotes = findViewById(R.id.blog_item_fragment__tvUpVotes);
        tvDownVotes = findViewById(R.id.blog_item_fragment__tvDownVotes);

        Button btnGoBack = findViewById(R.id.blog_item_fragment__btnGoBack);
        btnGoBack.setOnClickListener(v -> goBack());

        Button btnRefresh = findViewById(R.id.blog_item_fragment__btnRefresh);
        btnRefresh.setOnClickListener(v -> refresh());
    }

    private void refresh() {
        for (Listener listener : getListeners()) {
            listener.onRefreshClicked();
        }
    }

    private void goBack() {
        for (Listener listener : getListeners()) {
            listener.onBtnGoBackClicked();
        }
    }

    @Override public void showProgress() {
        progress.setVisibility(View.VISIBLE);
        llContent.setVisibility(View.GONE);
    }

    @Override public void hideProgress() {
        progress.setVisibility(View.GONE);
        llContent.setVisibility(View.VISIBLE);
    }

    @Override public void bindData(BlogItem blogItem) {
        tvTitle.setText(blogItem.getTitle());
        tvText.setText(blogItem.getText());
        tvViewCount.setText(String.valueOf(blogItem.getViewCount()));
        tvUpVotes.setText(String.valueOf(blogItem.getUpVotes()));
        tvDownVotes.setText(String.valueOf(blogItem.getDownVotes()));
    }
}
