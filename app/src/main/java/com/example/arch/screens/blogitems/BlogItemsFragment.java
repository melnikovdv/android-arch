package com.example.arch.screens.blogitems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.arch.screens.common.MainActivity;
import com.example.arch.screens.common.PresenterFactory;

public class BlogItemsFragment extends Fragment {

    private BlogItemsPresenter presenter;

    public static Fragment newInstance() {
        Fragment fragment = new BlogItemsFragment();
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        BlogItemsMvpView view = getMainActivity().getMvpViewFactory().getBlogItemsView(container, getContext());

        PresenterFactory presenterFactory = getMainActivity().getPresenterFactory();
        presenter = presenterFactory.getBlogItemsPresenter(getMainActivity());
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

    private MainActivity getMainActivity() {
        MainActivity activity = (MainActivity) getActivity();
        assert activity != null;
        return activity;
    }
}
