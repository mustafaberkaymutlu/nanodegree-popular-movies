package net.epictimes.nanodegreepopularmovies.features.detail.reviews;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.ReviewsDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.PagedReviews;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public class ReviewsPresenter extends MvpBasePresenter<ReviewsContract.View>
        implements ReviewsContract.Presenter {
    private static final int INITIAL_PAGE_INDEX = 0;

    @Repository
    @Inject
    ReviewsDataSource repository;

    private final ReviewsDataSource.GetReviewsCallback myGetReviewsCallback = new MyGetReviewsCallback();

    private int movieId;
    private int currentPageIndex = INITIAL_PAGE_INDEX;
    private int totalPages;

    @Inject
    ReviewsPresenter() {
    }

    @Override
    public void getReviews(int movieId) {
        this.movieId = movieId;

        getReviews();
    }

    @Override
    public void loadMore() {
        if (currentPageIndex < totalPages) {
            getReviews();
        }
    }

    private void getReviews() {
        repository.getReviews(movieId, currentPageIndex + 1, myGetReviewsCallback);
    }

    private class MyGetReviewsCallback implements ReviewsDataSource.GetReviewsCallback {
        @Override
        public void onReviewsReceived(PagedReviews pagedReviews) {
            currentPageIndex = pagedReviews.getPage();
            totalPages = pagedReviews.getTotalPages();

            ifViewAttached(view -> view.displayReviews(pagedReviews));
        }

        @Override
        public void onReviewsNotAvailable() {
            ifViewAttached(ReviewsContract.View::displayError);
        }
    }
}
