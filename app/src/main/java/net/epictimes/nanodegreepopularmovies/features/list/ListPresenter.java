package net.epictimes.nanodegreepopularmovies.features.list;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

class ListPresenter extends MvpBasePresenter<ListContract.View> implements ListContract.Presenter {
    private static final int INITIAL_PAGE_INDEX = 0;

    private int currentPageIndex = INITIAL_PAGE_INDEX;
    private SortCriteria currentSortCriteria = SortCriteria.POPULAR;

    private final MoviesDataSource.GetMoviesCallback getMoviesCallback = new MyGetMoviesCallback();

    @Repository
    @Inject
    MoviesDataSource moviesDataSource;

    @Inject
    ListPresenter() {
    }

    @Override
    public void getMovies() {
        ifViewAttached(ListContract.View::showLoading);
        getMoviesBySortCriteria();
    }

    @Override
    public void loadMore() {
        getMoviesBySortCriteria();
    }

    @Override
    public void switchSortCriteria(SortCriteria sortCriteria) {
        if (currentSortCriteria == sortCriteria) {
            return;
        }

        this.currentSortCriteria = sortCriteria;
        this.currentPageIndex = INITIAL_PAGE_INDEX;

        ifViewAttached(ListContract.View::showLoading);
        getMoviesBySortCriteria();
    }

    @Override
    public void userClickedMovie(Movie movie) {
        ifViewAttached(view -> view.goToMovieDetail(movie.getId()));
    }

    @Override
    public void userRefreshed() {
        this.currentPageIndex = INITIAL_PAGE_INDEX;

        getMoviesBySortCriteria();
    }

    private void getMoviesBySortCriteria() {
        switch (currentSortCriteria) {
            case POPULAR:
                moviesDataSource.getPopularMovies(currentPageIndex + 1, getMoviesCallback);
                break;
            case TOP_RATED:
                moviesDataSource.getTopRatedMovies(currentPageIndex + 1, getMoviesCallback);
                break;
            case FAVORITES:
                moviesDataSource.getFavoriteMovies(getMoviesCallback);
                break;
        }
    }

    private class MyGetMoviesCallback implements MoviesDataSource.GetMoviesCallback {
        @Override
        public void onMoviesReceived(final PagedMovies pagedMovies) {
            currentPageIndex = pagedMovies.getPage();

            ifViewAttached(view -> {
                final boolean isFirstPage = (currentPageIndex == INITIAL_PAGE_INDEX + 1);
                if (isFirstPage) {
                    view.clearMovies();
                }

                view.hideLoading();
                view.displayMovies(pagedMovies);
            });
        }

        @Override
        public void onMoviesNotAvailable() {
            ifViewAttached(view -> {
                view.hideLoading();
                view.displayGettingPopularMoviesError(currentSortCriteria);
            });
        }
    }
}
