package com.example.arch.screens.common.base;

import androidx.fragment.app.Fragment;
import com.example.arch.di.PresentationComponent;
import com.example.arch.screens.common.MainActivity;
import com.example.arch.screens.common.nav.BackPressDispatcher;

public abstract class BaseFragment extends Fragment {

    public BackPressDispatcher getBackPressDispatcher() {
        return (MainActivity) getActivity();
    }

    public PresentationComponent getPresentationComponent() {
        return ((MainActivity) getActivity()).getPresentationComponent();
    }
}
