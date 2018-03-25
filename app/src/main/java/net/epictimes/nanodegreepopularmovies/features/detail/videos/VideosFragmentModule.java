package net.epictimes.nanodegreepopularmovies.features.detail.videos;

import net.epictimes.nanodegreepopularmovies.di.scope.FragmentScoped;

import dagger.Binds;
import dagger.Module;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */
@Module
public abstract class VideosFragmentModule {

    @FragmentScoped
    @Binds
    abstract VideosContract.Presenter provideVideosPresenter(VideosPresenter videosPresenter);

}
