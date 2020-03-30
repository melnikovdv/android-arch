package com.example.arch.screens.blogitem2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.arch.R;
import com.example.arch.databinding.BlogItemDatabindingFragmentBinding;
import com.example.arch.screens.common.base.BaseFragment;

public class BlogItemMvvmFragment extends BaseFragment {

    public static final String ARG_ITEM_ID = "itemId";

    private BlogItemViewModel blogItemViewModel;

    public static Fragment newInstance(long itemId) {
        Fragment fragment = new BlogItemMvvmFragment();
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
        long id = getArguments().getLong(ARG_ITEM_ID);
        ViewModelProvider viewModelProvider = getPresentationRoot().getViewModelFactory().getViewModelProvider(id,
                this);
        blogItemViewModel = viewModelProvider.get(BlogItemViewModel.class);
        blogItemViewModel.updateScreenNavigator(getPresentationRoot().getScreenNavigator());
        blogItemViewModel.loadItem();

        BlogItemDatabindingFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.blog_item_databinding_fragment, container, false);
        binding.setVm(blogItemViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override public void onStart() {
        super.onStart();
        getBackPressDispatcher().registerListener(blogItemViewModel);
    }

    @Override public void onStop() {
        getBackPressDispatcher().unregisterListener(blogItemViewModel);
        super.onStop();
    }
}
