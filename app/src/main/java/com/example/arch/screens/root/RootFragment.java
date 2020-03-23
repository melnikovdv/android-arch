package com.example.arch.screens.root;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.arch.R;
import com.example.arch.screens.common.MainActivity;
import com.example.arch.screens.common.nav.ScreenNavigator;

public class RootFragment extends Fragment {

    private ScreenNavigator screenNavigator;

    private Button btnBlogItem;
    private Button btnBlogItems;
    private EditText edtBlogItemId;

    public static RootFragment newInstance() {
        return new RootFragment();
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //noinspection ConstantConditions
        screenNavigator = getMainActivity().getScreenNavigator();
    }

    @Nullable @Override public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.root_fragment, container, false);
        btnBlogItem = rootView.findViewById(R.id.root_fragment__btnBlogItem);
        edtBlogItemId = rootView.findViewById(R.id.root_fragment__edtBlogItemId);
        btnBlogItems = rootView.findViewById(R.id.root_fragment__btnBlogItems);
        setup();
        return rootView;
    }

    private void setup() {
        btnBlogItem.setOnClickListener(v -> screenNavigator.toBlogItem(Long.parseLong(edtBlogItemId.getText().toString())));
        btnBlogItems.setOnClickListener(v -> screenNavigator.toBlogItems());
    }

    @Nullable private MainActivity getMainActivity() {
        return (MainActivity) getActivity();
    }
}
