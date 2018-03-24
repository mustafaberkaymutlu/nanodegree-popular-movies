package net.epictimes.nanodegreepopularmovies.features.detail.overview;

import net.epictimes.nanodegreepopularmovies.di.scope.FragmentScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */
@Module
public abstract class OverviewFragmentModule {

    @FragmentScoped
    @Binds
    abstract OverviewContract.Presenter provideOverviewPresenter(OverviewPresenter overviewPresenter);

}
