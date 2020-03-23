package com.example.arch.screens.blogitem;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.arch.App;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.common.MainActivity;
import com.example.arch.util.ThreadPoster;

public class BlogItemFragment extends Fragment {

    public static final String ARG_ITEM_ID = "itemId";

    private BlogItemPresenter presenter;

    public static Fragment newInstance(long itemId) {
        Fragment fragment = new BlogItemFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_ITEM_ID, itemId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long id = getArguments().getLong(ARG_ITEM_ID);
        //noinspection ConstantConditions
        presenter = new BlogItemPresenter(id, getMainActivity().getScreenNavigator(), getMainActivity(),
                getFindBlogItemService(), getMainThreadPoster());
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        BlogItemMvpView view = new BlogItemMvpViewImpl(inflater, container);
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

    @Nullable private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    private FindBlogItemService getFindBlogItemService() {
        App app = (App) getActivity().getApplication();
        assert app != null;
        return app.provideFindBlogItemService();
    }

    private ThreadPoster getMainThreadPoster() {
        App app = (App) getActivity().getApplication();
        assert app != null;
        return app.provideMainThreadPoster();
    }
}
