package net.epictimes.nanodegreepopularmovies.features.list;

import net.epictimes.nanodegreepopularmovies.di.scope.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

@Module
public abstract class ListActivityModule {

    @ActivityScoped
    @Binds
    abstract ListContract.Presenter provideListPresenter(ListPresenter listPresenter);

}
