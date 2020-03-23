package com.example.arch.screens.blogitems;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.arch.App;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.blogitem.BlogItemMvpView;
import com.example.arch.screens.blogitem.BlogItemMvpViewImpl;
import com.example.arch.screens.blogitem.BlogItemPresenter;
import com.example.arch.screens.common.MainActivity;
import com.example.arch.util.ThreadPoster;

import static com.example.arch.screens.blogitem.BlogItemFragment.ARG_ITEM_ID;

public class BlogItemsFragment extends Fragment {

    private BlogItemsPresenter presenter;

    public static Fragment newInstance() {
        Fragment fragment = new BlogItemsFragment();
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection ConstantConditions
        presenter = new BlogItemsPresenter(getMainActivity().getScreenNavigator(), getMainActivity(),
                getFindBlogItemService(), getMainThreadPoster());
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        BlogItemsMvpView view = new BlogItemsMvpViewImpl(inflater, container, getContext());
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
