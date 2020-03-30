package com.example.arch.screens.common.base;

import androidx.fragment.app.Fragment;
import com.example.arch.di.PresentationRoot;
import com.example.arch.screens.common.MainActivity;
import com.example.arch.screens.common.nav.BackPressDispatcher;

public abstract class BaseFragment extends Fragment {

    public PresentationRoot getPresentationRoot() {
        MainActivity mainActivity = (MainActivity) getActivity();
        return mainActivity.getPresentationRoot();
    }

    public BackPressDispatcher getBackPressDispatcher() {
        return (MainActivity) getActivity();
    }
}
