package com.example.arch.screens.blogitem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.arch.screens.common.MvpViewFactory;
import com.example.arch.screens.common.PresenterFactory;
import com.example.arch.screens.common.base.BaseFragment;

import javax.inject.Inject;

public class BlogItemMvpFragment extends BaseFragment {

    public static final String ARG_ITEM_ID = "itemId";

    @Inject MvpViewFactory mvpViewFactory;
    @Inject PresenterFactory presenterFactory;

    private BlogItemPresenter presenter;

    public static Fragment newInstance(long itemId) {
        Fragment fragment = new BlogItemMvpFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ITEM_ID, itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        getPresentationComponent().inject(this);
        BlogItemMvpView view = mvpViewFactory.getBlogItemMvpView(container);

        long id = getArguments().getLong(ARG_ITEM_ID);
        presenter = presenterFactory.getBlogItemPresenter(id, getBackPressDispatcher());
        presenter.bindView(view);
        return view.getRootView();
    }

    @Override public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override public void onStop() {
        presenter.onStop();
        super.onStop();
    }

    @Override public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
