package net.epictimes.nanodegreepopularmovies.features.detail.trailers;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public class TrailersPresenter extends MvpBasePresenter<TrailersContract.View>
        implements TrailersContract.Presenter {

    @Repository
    @Inject
    MoviesDataSource repository;

    @Inject
    public TrailersPresenter() {
    }

    @Override
    public void getTrailers(int movieId) {
        // TODO get trailers
    }
}
