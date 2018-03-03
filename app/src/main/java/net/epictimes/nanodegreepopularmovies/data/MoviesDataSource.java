package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public interface MoviesDataSource {

    void getPopularMovies(GetPopularMoviesCallback callback);

    interface GetPopularMoviesCallback {

        void onPopularMoviesDataReceived(PagedMovies pagedMovies);

        void onPopularMoviesDataNotAvailable();

    }

}
