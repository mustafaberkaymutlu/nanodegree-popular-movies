package net.epictimes.nanodegreepopularmovies.features.list;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public interface ListContract {

    interface View extends MvpView {

        void displayMovies(PagedMovies pagedMovies);

        void displayGettingPopularMoviesError();

        void goToMovieDetail(int movieId);

    }

    interface Presenter extends MvpPresenter<View> {

        void getPopularMovies();

        void loadMore();

        void userClickedMovie(Movie movie);

    }

}
