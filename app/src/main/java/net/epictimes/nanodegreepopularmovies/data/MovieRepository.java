package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.di.qualifier.RemoteDataSource;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public class MovieRepository implements MoviesDataSource {

    @RemoteDataSource
    @Inject
    MoviesDataSource remoteDataSource;

    @Inject
    public MovieRepository() {
    }

    @Override
    public void getPopularMovies(int page, GetPopularMoviesCallback callback) {
        remoteDataSource.getPopularMovies(page, callback);
    }
}
