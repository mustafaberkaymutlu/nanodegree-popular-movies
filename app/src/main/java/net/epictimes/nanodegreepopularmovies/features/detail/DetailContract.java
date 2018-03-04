package net.epictimes.nanodegreepopularmovies.features.detail;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public interface DetailContract {

    interface View extends MvpView {

        void displayMovie(Movie movie);

        void displayMovieError();

    }

    interface Presenter extends MvpPresenter<View> {

        void getMovie(int movieId);

    }

}
