package net.epictimes.nanodegreepopularmovies.features.detail;

import net.epictimes.nanodegreepopularmovies.di.scope.ActivityScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

@Module
public abstract class DetailActivityModule {

    @ActivityScoped
    @Binds
    abstract DetailContract.Presenter provideDetailPresenter(DetailPresenter detailPresenter);

}
