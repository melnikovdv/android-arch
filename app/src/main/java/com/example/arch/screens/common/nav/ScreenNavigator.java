package com.example.arch.screens.common.nav;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.example.arch.R;
import com.example.arch.screens.blogitem.BlogItemFragment;
import com.example.arch.screens.blogitems.BlogItemsFragment;
import com.example.arch.screens.root.RootFragment;
import com.ncapdevi.fragnav.FragNavController;
import org.jetbrains.annotations.NotNull;

public class ScreenNavigator implements FragNavController.RootFragmentListener {

    private final FragNavController fragNavController;

    public ScreenNavigator(FragmentManager fragmentManager, Bundle savedInstanceState)  {
        fragNavController = new FragNavController(fragmentManager, R.id.container);
        fragNavController.setRootFragmentListener(this);
        fragNavController.initialize(FragNavController.TAB1, savedInstanceState);
    }

    public void toBlogItem(long id) {
        fragNavController.pushFragment(BlogItemFragment.newInstance(id));
    }

    public void toBlogItems() {
        fragNavController.pushFragment(BlogItemsFragment.newInstance());
    }

    @Override public int getNumberOfRootFragments() {
        return 1;
    }

    @NotNull @Override public Fragment getRootFragment(int index) {
        return RootFragment.newInstance();
    }

    public void onSaveInstanceState(Bundle outState) {
        fragNavController.onSaveInstanceState(outState);
    }

    public void navigateUp() {
        fragNavController.popFragment();
    }
}
