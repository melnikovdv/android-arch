package com.example.arch.screens.blogitem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.arch.screens.common.MainActivity;
import com.example.arch.screens.common.MvpViewFactory;

public class BlogItemMvpFragment extends Fragment {

    public static final String ARG_ITEM_ID = "itemId";

    private BlogItemPresenter presenter;

    public static Fragment newInstance(long itemId) {
        Fragment fragment = new BlogItemMvpFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ITEM_ID, itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        MvpViewFactory mvpViewFactory = getMainActivity().getMvpViewFactory();
        BlogItemMvpView view = mvpViewFactory.getBlogItemMvpView(container);

        long id = getArguments().getLong(ARG_ITEM_ID);
        presenter = getMainActivity().getPresenterFactory().getBlogItemPresenter(id, getMainActivity());
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
