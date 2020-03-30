package com.example.arch.di;

import com.example.arch.screens.blogitem.BlogItemMvpFragment;
import com.example.arch.screens.blogitem2.BlogItemMvvmFragment;
import com.example.arch.screens.blogitems.BlogItemsFragment;
import com.example.arch.screens.common.MainActivity;
import com.example.arch.screens.common.base.BaseActivity;
import com.example.arch.screens.root.RootFragment;
import dagger.Subcomponent;

@Presentation
@Subcomponent(modules = {
        PresentationModule.class
})
public interface PresentationComponent {

    void inject(MainActivity mainActivity);

    void inject(RootFragment rootFragment);

    void inject(BlogItemMvpFragment blogItemMvpFragment);

    void inject(BlogItemMvvmFragment blogItemMvvmFragment);

    void inject(BlogItemsFragment blogItemsFragment);

    void inject(BaseActivity baseActivity);
}
