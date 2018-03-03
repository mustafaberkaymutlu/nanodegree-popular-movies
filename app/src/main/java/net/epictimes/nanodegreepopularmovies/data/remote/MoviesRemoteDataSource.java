package net.epictimes.nanodegreepopularmovies.data.remote;

import android.support.annotation.NonNull;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
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
    public MoviesRemoteDataSource() {
    }

    @Override
    public void getPopularMovies(final GetPopularMoviesCallback callback) {
        services.getPopularMovies()
                .enqueue(new Callback<PagedMovies>() {
                    @Override
                    public void onResponse(@NonNull Call<PagedMovies> call,
                                           @NonNull Response<PagedMovies> response) {
                        final PagedMovies body = response.body();

                        if (response.isSuccessful() && body != null) {
                            callback.onPopularMoviesDataReceived(body);
                        } else {
                            callback.onPopularMoviesDataNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<PagedMovies> call,
                                          @NonNull Throwable t) {
                        callback.onPopularMoviesDataNotAvailable();
                    }
                });
    }
}
