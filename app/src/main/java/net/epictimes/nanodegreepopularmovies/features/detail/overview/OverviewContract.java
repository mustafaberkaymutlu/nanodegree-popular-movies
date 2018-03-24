package net.epictimes.nanodegreepopularmovies.features.detail.overview;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public interface OverviewContract {

    interface View extends MvpView {

        void displayMovieOverview(Movie movie);

        void displayError();

    }

    interface Presenter extends MvpPresenter<View> {

        void getMovieOverview(int movieId);

    }

}
