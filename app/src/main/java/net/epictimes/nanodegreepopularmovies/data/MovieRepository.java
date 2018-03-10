package net.epictimes.nanodegreepopularmovies.data;

import android.util.SparseArray;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;
import net.epictimes.nanodegreepopularmovies.di.qualifier.RemoteDataSource;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public class MovieRepository implements MoviesDataSource {
    private final SparseArray<Movie> cache = new SparseArray<>();

    @RemoteDataSource
    @Inject
    MoviesDataSource remoteDataSource;

    @Inject
    MovieRepository() {
    }

    @Override
    public void getPopularMovies(int page, GetMoviesCallback callback) {
        remoteDataSource.getPopularMovies(page, new GetMoviesCallback() {
            @Override
            public void onMoviesReceived(PagedMovies pagedMovies) {
                addMoviesToCache(pagedMovies.getResults());
                callback.onMoviesReceived(pagedMovies);
            }

            @Override
            public void onMoviesNotAvailable() {
                callback.onMoviesNotAvailable();
            }
        });
    }

    @Override
    public void getTopRatedMovies(int page, GetMoviesCallback callback) {
        remoteDataSource.getTopRatedMovies(page, new GetMoviesCallback() {
            @Override
            public void onMoviesReceived(PagedMovies pagedMovies) {
                addMoviesToCache(pagedMovies.getResults());
                callback.onMoviesReceived(pagedMovies);
            }

            @Override
            public void onMoviesNotAvailable() {
                callback.onMoviesNotAvailable();
            }
        });
    }

    @Override
    public void getMovieById(int movieId, GetMovieCallback callback) {
        final Movie movie = cache.get(movieId);

        if (movie == null) {
            remoteDataSource.getMovieById(movieId, callback);
        } else {
            callback.onMovieReceived(movie);
        }
    }

    private void addMoviesToCache(Iterable<Movie> movies) {
        for (Movie movie : movies) {
            cache.put(movie.getId(), movie);
        }
    }

}
