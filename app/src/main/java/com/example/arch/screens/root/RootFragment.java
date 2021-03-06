package com.example.arch.screens.root;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.arch.R;
import com.example.arch.screens.common.base.BaseFragment;
import com.example.arch.screens.common.nav.ScreenNavigator;

import javax.inject.Inject;

public class RootFragment extends BaseFragment {

    @Inject ScreenNavigator screenNavigator;

    private Button btnBlogItem;
    private EditText edtBlogItemId;

    private Button btnBlogItems;

    private Button btnBlogItemVm;
    private EditText edtBlogItemIdVm;

    public static RootFragment newInstance() {
        return new RootFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPresentationComponent().inject(this);
    }

    @Override public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.root_fragment, container, false);

        btnBlogItem = rootView.findViewById(R.id.root_fragment__btnBlogItem);
        edtBlogItemId = rootView.findViewById(R.id.root_fragment__edtBlogItemId);

        btnBlogItems = rootView.findViewById(R.id.root_fragment__btnBlogItems);

        btnBlogItemVm = rootView.findViewById(R.id.root_fragment__btnBlogItemVm);
        edtBlogItemIdVm = rootView.findViewById(R.id.root_fragment__edtBlogItemIdVm);

        setup();
        return rootView;
    }

    private void setup() {
        btnBlogItem.setOnClickListener(v -> {
            long id = Long.parseLong(edtBlogItemId.getText().toString());
            screenNavigator.toBlogItem(id);
        });

        btnBlogItems.setOnClickListener(v -> screenNavigator.toBlogItems());

        btnBlogItemVm.setOnClickListener(view -> {
            long id = Long.parseLong(edtBlogItemIdVm.getText().toString());
            screenNavigator.toBlogItemVm(id);
        });
    }
}
