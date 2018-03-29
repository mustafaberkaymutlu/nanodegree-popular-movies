package net.epictimes.nanodegreepopularmovies.features.detail.overview;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public class OverviewPresenter extends MvpBasePresenter<OverviewContract.View>
        implements OverviewContract.Presenter {

    @Repository
    @Inject
    MoviesDataSource repository;

    private final MoviesDataSource.GetMovieCallback myGetMovieCallback = new MyGetMovieCallback();

    @Inject
    OverviewPresenter() {
    }

    @Override
    public void getMovieOverview(int movieId) {
        repository.getMovieById(movieId, myGetMovieCallback);
    }

    private class MyGetMovieCallback implements MoviesDataSource.GetMovieCallback {
        @Override
        public void onMovieReceived(Movie movie) {
            ifViewAttached(view -> view.displayMovieOverview(movie));
        }

        @Override
        public void onMovieNotAvailable() {
            ifViewAttached(OverviewContract.View::displayError);
        }
    }
}
