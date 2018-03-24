package net.epictimes.nanodegreepopularmovies.features.detail.trailers;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public interface TrailersContract {

    interface View extends MvpView {

        void displayTrailers();

        void displayError();

    }

    interface Presenter extends MvpPresenter<View> {

        void getTrailers(int movieId);

    }

}
