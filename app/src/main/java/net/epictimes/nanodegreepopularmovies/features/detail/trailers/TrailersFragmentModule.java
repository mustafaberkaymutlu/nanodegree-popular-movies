package net.epictimes.nanodegreepopularmovies.features.detail.trailers;

import net.epictimes.nanodegreepopularmovies.di.scope.FragmentScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */
@Module
public abstract class TrailersFragmentModule {

    @FragmentScoped
    @Binds
    abstract TrailersContract.Presenter provideOverviewPresenter(TrailersPresenter trailersPresenter);

}
