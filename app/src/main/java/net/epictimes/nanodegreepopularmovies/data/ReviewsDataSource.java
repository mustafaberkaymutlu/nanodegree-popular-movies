package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.data.model.PagedReviews;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public interface ReviewsDataSource {

    void getReviews(int movieId, int page, GetReviewsCallback callback);

    interface GetReviewsCallback {

        void onReviewsReceived(PagedReviews pagedReviews);

        void onReviewsNotAvailable();

    }

}
