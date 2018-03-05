package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public interface MoviesDataSource {

    void getPopularMovies(int page, GetMoviesCallback callback);

    void getTopRatedMovies(int page, GetMoviesCallback callback);

    void getMovieById(int movieId, GetMovieCallback callback);

    interface GetMoviesCallback {

        void onMoviesReceived(PagedMovies pagedMovies);

        void onMoviesNotAvailable();

    }

    interface GetMovieCallback {

        void onMovieReceived(Movie movie);

        void onMovieNotAvailable();

    }

}
