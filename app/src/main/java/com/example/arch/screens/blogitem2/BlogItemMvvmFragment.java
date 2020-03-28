package com.example.arch.screens.blogitem2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.arch.App;
import com.example.arch.R;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.databinding.BlogItemDatabindingFragmentBinding;
import com.example.arch.screens.common.MainActivity;
import org.jetbrains.annotations.NotNull;

public class BlogItemMvvmFragment extends Fragment {

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
        long id = getArguments().getLong(ARG_ITEM_ID);

        ViewModelProvider.Factory factory = getViewModelFactory(id);
        blogItemViewModel = new ViewModelProvider(this, factory).get(BlogItemViewModel.class);
        blogItemViewModel.loadItem();
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        BlogItemDatabindingFragmentBinding binding = DataBindingUtil.inflate(inflater,
                R.layout.blog_item_databinding_fragment, container, false);
        binding.setVm(blogItemViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

    @Override public void onStart() {
        super.onStart();
        getMainActivity().registerListener(blogItemViewModel);
    }

    @Override public void onStop() {
        super.onStop();
        getMainActivity().unregisterListener(blogItemViewModel);
    }

    private FindBlogItemService getFindBlogItemService() {
        App app = (App) getActivity().getApplication();
        assert app != null;
        return app.provideFindBlogItemService();
    }

    @Nullable private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }

    @NotNull private ViewModelProvider.Factory getViewModelFactory(long id) {
        return new ViewModelProvider.Factory() {
            @NonNull @Override public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
                if (modelClass == BlogItemViewModel.class) {
                    BlogItemViewModel blogItemViewModel = new BlogItemViewModel(id, getFindBlogItemService(),
                            getMainActivity().getScreenNavigator());
                    return (T) blogItemViewModel;
                }
                throw new IllegalStateException("Unknown ViewModel");
            }
        };
    }
}