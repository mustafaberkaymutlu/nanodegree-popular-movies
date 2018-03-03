package net.epictimes.nanodegreepopularmovies.di;

import net.epictimes.nanodegreepopularmovies.MainActivity;
import net.epictimes.nanodegreepopularmovies.di.scope.ActivityScoped;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

@Module
public abstract class ActivityBuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivityInjector();

}
