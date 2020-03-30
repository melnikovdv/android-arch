package com.example.arch.di;

import dagger.Component;

import javax.inject.Singleton;

@Singleton @Component(modules = {
        AppModule.class,
        AppModule.BindsModule.class
})
public interface AppComponent {

    PresentationComponent newPresentationComponent(PresentationModule presentationModule);
}
