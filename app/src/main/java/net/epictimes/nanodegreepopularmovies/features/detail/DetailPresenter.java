package net.epictimes.nanodegreepopularmovies.features.detail;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 4.03.2018.
 */

public class DetailPresenter extends MvpBasePresenter<DetailContract.View>
        implements DetailContract.Presenter {

    private MoviesDataSource.GetMovieCallback getMovieCallback = new MyGetMovieCallback();

    @Repository
    @Inject
    MoviesDataSource moviesRepository;

    private Movie movie;
    private boolean isFavorite;

    @Inject
    DetailPresenter() {
    }

    @Override
    public void getMovie(int movieId) {
        if (movieId == -1) {
            ifViewAttached(DetailContract.View::displayMovieError);
            return;
        }

        moviesRepository.getMovieById(movieId, getMovieCallback);
    }

    @Override
    public void switchFavoriteStatus() {
        if (isFavorite) {
            moviesRepository.removeFromFavorites(movie.getId());
            isFavorite = false;
            ifViewAttached(DetailContract.View::displayRemovedFromFavorites);
        } else {
            moviesRepository.addToFavorites(movie);
            isFavorite = true;
            ifViewAttached(DetailContract.View::displayAddedToFavorites);
        }
    }

    private class MyGetMovieCallback implements MoviesDataSource.GetMovieCallback {
        @Override
        public void onMovieReceived(Movie movie) {
            DetailPresenter.this.movie = movie;

            moviesRepository.isMovieFavorite(movie.getId(), isFavorite -> {
                DetailPresenter.this.isFavorite = isFavorite;
                ifViewAttached(view -> view.displayFavoriteStatus(isFavorite));
            });

            ifViewAttached(view -> view.displayMovie(movie));
        }

        @Override
        public void onMovieNotAvailable() {
            ifViewAttached(DetailContract.View::displayMovieError);
        }
    }
}
