package net.epictimes.nanodegreepopularmovies.features.detail.reviews;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import net.epictimes.nanodegreepopularmovies.data.model.PagedReviews;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public interface ReviewsContract {

    interface View extends MvpView {

        void displayReviews(PagedReviews pagedReviews);

        void displayError();

    }

    interface Presenter extends MvpPresenter<View> {

        void getReviews(int movieId);

        void loadMore();

    }

}
