package com.melikeey;

import android.app.Application;

import com.melikeey.di.component.ApplicationComponent;
import com.melikeey.di.component.DaggerApplicationComponent;
import com.melikeey.di.module.ApplicationModule;

public class MusicApp extends Application {

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();


        mApplicationComponent.inject(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }

}