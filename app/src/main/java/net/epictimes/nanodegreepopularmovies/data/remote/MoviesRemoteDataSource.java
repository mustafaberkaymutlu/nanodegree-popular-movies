package net.epictimes.nanodegreepopularmovies.data.remote;

import android.support.annotation.NonNull;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public class MoviesRemoteDataSource implements MoviesDataSource {

    @Inject
    Services services;

    @Inject
    MoviesRemoteDataSource() {
    }

    @Override
    public void getPopularMovies(int page, final GetMoviesCallback callback) {
        services.getPopularMovies(page)
                .enqueue(new Callback<PagedMovies>() {
                    @Override
                    public void onResponse(@NonNull Call<PagedMovies> call,
                                           @NonNull Response<PagedMovies> response) {
                        final PagedMovies body = response.body();

                        if (response.isSuccessful() && body != null) {
                            callback.onMoviesReceived(body);
                        } else {
                            callback.onMoviesNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PagedMovies> call,
                                          @NonNull Throwable t) {
                        callback.onMoviesNotAvailable();
                    }
                });
    }

    @Override
    public void getTopRatedMovies(int page, GetMoviesCallback callback) {
        services.getTopRatedMovies(page)
                .enqueue(new Callback<PagedMovies>() {
                    @Override
                    public void onResponse(@NonNull Call<PagedMovies> call,
                                           @NonNull Response<PagedMovies> response) {
                        final PagedMovies body = response.body();

                        if (response.isSuccessful() && body != null) {
                            callback.onMoviesReceived(body);
                        } else {
                            callback.onMoviesNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PagedMovies> call,
                                          @NonNull Throwable t) {
                        callback.onMoviesNotAvailable();
                    }
                });
    }

    @Override
    public void getFavoriteMovies(GetMoviesCallback callback) {
        throw new UnsupportedOperationException("Getting favorites using remote is not supported. ");
    }

    @Override
    public void getMovieById(int movieId, GetMovieCallback callback) {
        services.getMovie(movieId)
                .enqueue(new Callback<Movie>() {
                    @Override
                    public void onResponse(@NonNull Call<Movie> call,
                                           @NonNull Response<Movie> response) {
                        final Movie body = response.body();

                        if (response.isSuccessful() && body != null) {
                            callback.onMovieReceived(body);
                        } else {
                            callback.onMovieNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<Movie> call,
                                          @NonNull Throwable t) {
                        callback.onMovieNotAvailable();
                    }
                });
    }

    @Override
    public void addToFavorites(Movie movie) {
        throw new UnsupportedOperationException("Adding to favorites using remote is not supported. ");
    }

    @Override
    public void removeFromFavorites(int movieId) {
        throw new UnsupportedOperationException("Removing from favorites using remote is not supported. ");
    }

    @Override
    public void isMovieFavorite(int movieId, IsFavoriteCallback callback) {
        throw new UnsupportedOperationException("Querying for favorites using remote is not supported. ");
    }
}
