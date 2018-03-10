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

        void clearMovies();

        void showLoading();

        void hideLoading();

        void displayGettingPopularMoviesError(SortCriteria sortCriteria);

        void goToMovieDetail(int movieId);

    }

    interface Presenter extends MvpPresenter<View> {

        void getMovies();

        void loadMore();

        void switchSortCriteria(SortCriteria sortCriteria);

        void userClickedMovie(Movie movie);

        void userRefreshed();
    }

}
