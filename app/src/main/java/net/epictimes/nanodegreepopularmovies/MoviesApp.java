package net.epictimes.nanodegreepopularmovies;

import android.app.Activity;
import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

import net.epictimes.nanodegreepopularmovies.di.DaggerSingletonComponent;
import net.epictimes.nanodegreepopularmovies.di.SingletonComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public class MoviesApp extends Application implements HasActivityInjector {
    private SingletonComponent singletonComponent;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        initSingletonComponent();

        initTimber();
    }

    private void initSingletonComponent() {
        singletonComponent = DaggerSingletonComponent.builder()
                .application(this)
                .build();

        singletonComponent.inject(this);
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
