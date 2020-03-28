package com.example.arch.screens.blogitem2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.arch.App;
import com.example.arch.R;
import com.example.arch.blog.model.BlogItem;
import com.example.arch.blog.service.FindBlogItemService;
import com.example.arch.screens.common.MainActivity;
import org.jetbrains.annotations.NotNull;

public class BlogItemMvvmFragment extends Fragment {

    public static final String ARG_ITEM_ID = "itemId";

    private ProgressBar progress;
    private LinearLayout llContent;
    private TextView tvTitle;
    private TextView tvText;
    private TextView tvViewCount;
    private TextView tvUpVotes;
    private TextView tvDownVotes;

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
        blogItemViewModel.getLoadingLiveData().observe(this, this::showLoading);
        blogItemViewModel.getBlogItemLiveData().observe(this, this::bindData);
        blogItemViewModel.loadItem();
    }

    private void showLoading(Boolean loading) {
        if (loading) {
            progress.setVisibility(View.VISIBLE);
            llContent.setVisibility(View.GONE);
        } else {
            progress.setVisibility(View.GONE);
            llContent.setVisibility(View.VISIBLE);
        }
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.blog_item_fragment, container, false);

        progress = rootView.findViewById(R.id.blog_item_fragment__progress);
        llContent = rootView.findViewById(R.id.blog_item_fragment__llContent);
        tvTitle = rootView.findViewById(R.id.blog_item_fragment__tvTitle);
        tvText = rootView.findViewById(R.id.blog_item_fragment__tvText);
        tvViewCount = rootView.findViewById(R.id.blog_item_fragment__tvViewCount);
        tvUpVotes = rootView.findViewById(R.id.blog_item_fragment__tvUpVotes);
        tvDownVotes = rootView.findViewById(R.id.blog_item_fragment__tvDownVotes);

        Button btnGoBack = rootView.findViewById(R.id.blog_item_fragment__btnGoBack);
        btnGoBack.setOnClickListener(v -> blogItemViewModel.onGoBackClicked());

        Button btnRefresh = rootView.findViewById(R.id.blog_item_fragment__btnRefresh);
        btnRefresh.setOnClickListener(v -> blogItemViewModel.onRefreshClicked());

        return rootView;
    }

    private void bindData(BlogItem blogItem) {
        tvTitle.setText(blogItem.getTitle());
        tvText.setText(blogItem.getText());
        tvViewCount.setText(String.valueOf(blogItem.getViewCount()));
        tvUpVotes.setText(String.valueOf(blogItem.getUpVotes()));
        tvDownVotes.setText(String.valueOf(blogItem.getDownVotes()));
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
