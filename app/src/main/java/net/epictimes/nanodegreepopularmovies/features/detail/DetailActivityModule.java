package net.epictimes.nanodegreepopularmovies.features.detail;

import net.epictimes.nanodegreepopularmovies.di.scope.ActivityScoped;
import net.epictimes.nanodegreepopularmovies.di.scope.FragmentScoped;
import net.epictimes.nanodegreepopularmovies.features.detail.overview.OverviewFragment;
import net.epictimes.nanodegreepopularmovies.features.detail.overview.OverviewFragmentModule;
import net.epictimes.nanodegreepopularmovies.features.detail.reviews.ReviewsFragment;
import net.epictimes.nanodegreepopularmovies.features.detail.reviews.ReviewsFragmentModule;
import net.epictimes.nanodegreepopularmovies.features.detail.videos.VideosFragment;
import net.epictimes.nanodegreepopularmovies.features.detail.videos.VideosFragmentModule;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

@Module
public abstract class DetailActivityModule {

    @ActivityScoped
    @Binds
    abstract DetailContract.Presenter provideDetailPresenter(DetailPresenter detailPresenter);

    @FragmentScoped
    @ContributesAndroidInjector(modules = OverviewFragmentModule.class)
    abstract OverviewFragment contributeOverviewFragmentInjector();

    @FragmentScoped
    @ContributesAndroidInjector(modules = VideosFragmentModule.class)
    abstract VideosFragment contributeVideosFragmentInjector();

    @FragmentScoped
    @ContributesAndroidInjector(modules = ReviewsFragmentModule.class)
    abstract ReviewsFragment contributeReviewsFragmentInjector();

}
