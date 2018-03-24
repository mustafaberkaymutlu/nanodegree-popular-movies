package net.epictimes.nanodegreepopularmovies.features.detail.reviews;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public interface ReviewsContract {

    interface View extends MvpView {

        void displayReviews();

        void displayError();

    }

    interface Presenter extends MvpPresenter<View> {

        void getReviews(int movieId);

    }

}
