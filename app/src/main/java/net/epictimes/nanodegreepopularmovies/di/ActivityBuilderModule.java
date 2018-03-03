package net.epictimes.nanodegreepopularmovies.di;

import net.epictimes.nanodegreepopularmovies.di.scope.ActivityScoped;
import net.epictimes.nanodegreepopularmovies.features.list.ListActivity;
import net.epictimes.nanodegreepopularmovies.features.list.ListActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

@Module
public abstract class ActivityBuilderModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = {ListActivityModule.class})
    abstract ListActivity contributeMainActivityInjector();

}
