package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;
import net.epictimes.nanodegreepopularmovies.di.qualifier.RemoteDataSource;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public class MovieRepository implements MoviesDataSource {

    private final Map<Integer, Movie> cache = new HashMap<>();

    @RemoteDataSource
    @Inject
    MoviesDataSource remoteDataSource;

    @Inject
    MovieRepository() {
    }

    @Override
    public void getPopularMovies(int page, GetPopularMoviesCallback callback) {
        remoteDataSource.getPopularMovies(page, new GetPopularMoviesCallback() {
            @Override
            public void onPopularMoviesDataReceived(PagedMovies pagedMovies) {
                addMoviesToCache(pagedMovies.getResults());
                callback.onPopularMoviesDataReceived(pagedMovies);
            }

            @Override
            public void onPopularMoviesDataNotAvailable() {
                callback.onPopularMoviesDataNotAvailable();
            }
        });
    }

    @Override
    public void getMovieById(int movieId, GetMovieCallback callback) {
        if (cache.containsKey(movieId)) {
            callback.onMovieReceived(cache.get(movieId));
        } else {
            remoteDataSource.getMovieById(movieId, callback);
        }
    }

    private void addMoviesToCache(Iterable<Movie> movies) {
        for (Movie movie : movies) {
            cache.put(movie.getId(), movie);
        }
    }

}
