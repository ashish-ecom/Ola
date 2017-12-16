package com.olacabs.play.di.component;

import android.app.Application;

import com.olacabs.play.OlaPlay;
import com.olacabs.play.di.builder.ActivityBuilder;
import com.olacabs.play.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Generated by Dipendra on 16/12/17
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }

    void inject(OlaPlay app);
}
