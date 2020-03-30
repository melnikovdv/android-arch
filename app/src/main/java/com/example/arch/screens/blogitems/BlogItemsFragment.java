package com.example.arch.screens.blogitems;

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

public class BlogItemsFragment extends BaseFragment {

    @Inject MvpViewFactory mvpViewFactory;
    @Inject PresenterFactory presenterFactory;

    private BlogItemsPresenter presenter;

    public static Fragment newInstance() {
        Fragment fragment = new BlogItemsFragment();
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        BlogItemsMvpView view = mvpViewFactory.getBlogItemsView(container, getContext());
        presenter = presenterFactory.getBlogItemsPresenter(getBackPressDispatcher());
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
