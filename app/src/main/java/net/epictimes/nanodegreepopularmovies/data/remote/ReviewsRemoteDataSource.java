package net.epictimes.nanodegreepopularmovies.data.remote;

import net.epictimes.nanodegreepopularmovies.data.ReviewsDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.PagedReviews;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public class ReviewsRemoteDataSource implements ReviewsDataSource {

    @Inject
    Services services;

    @Inject
    public ReviewsRemoteDataSource() {
    }

    @Override
    public void getReviews(int movieId, int page, GetReviewsCallback callback) {
        services.getReviews(movieId, page)
                .enqueue(new Callback<PagedReviews>() {
                    @Override
                    public void onResponse(Call<PagedReviews> call, Response<PagedReviews> response) {
                        final PagedReviews body = response.body();

                        if (response.isSuccessful() && body != null) {
                            callback.onReviewsReceived(body);
                        } else {
                            callback.onReviewsNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<PagedReviews> call, Throwable t) {
                        callback.onReviewsNotAvailable();
                    }
                });
    }
}
