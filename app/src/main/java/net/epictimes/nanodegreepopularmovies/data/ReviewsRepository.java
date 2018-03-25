package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.di.qualifier.RemoteDataSource;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public class ReviewsRepository implements ReviewsDataSource {

    @RemoteDataSource
    @Inject
    ReviewsDataSource remoteDataSource;

    @Inject
    public ReviewsRepository() {
    }

    @Override
    public void getReviews(int movieId, int page, GetReviewsCallback callback) {
        remoteDataSource.getReviews(movieId, page, callback);
    }
}
