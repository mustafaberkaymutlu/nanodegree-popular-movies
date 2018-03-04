package net.epictimes.nanodegreepopularmovies.features.list;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

class ListPresenter extends MvpBasePresenter<ListContract.View> implements ListContract.Presenter {
    private int currentPageIndex = 1;

    @Repository
    @Inject
    MoviesDataSource moviesDataSource;

    @Inject
    ListPresenter() {
    }

    @Override
    public void getPopularMovies() {
        moviesDataSource.getPopularMovies(currentPageIndex,
                new MoviesDataSource.GetPopularMoviesCallback() {
                    @Override
                    public void onPopularMoviesDataReceived(final PagedMovies pagedMovies) {
                        ifViewAttached(view -> view.displayMovies(pagedMovies));
                    }

                    @Override
                    public void onPopularMoviesDataNotAvailable() {
                        ifViewAttached(ListContract.View::displayGettingPopularMoviesError);
                    }
                });
    }

    @Override
    public void loadMore() {
        moviesDataSource.getPopularMovies(currentPageIndex + 1,
                new MoviesDataSource.GetPopularMoviesCallback() {
                    @Override
                    public void onPopularMoviesDataReceived(final PagedMovies pagedMovies) {
                        currentPageIndex++;

                        ifViewAttached(view -> view.displayMovies(pagedMovies));
                    }

                    @Override
                    public void onPopularMoviesDataNotAvailable() {
                        ifViewAttached(ListContract.View::displayGettingPopularMoviesError);
                    }
                });
    }
}
