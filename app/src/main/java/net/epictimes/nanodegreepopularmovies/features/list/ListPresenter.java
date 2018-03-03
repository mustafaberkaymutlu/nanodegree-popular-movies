package net.epictimes.nanodegreepopularmovies.features.list;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

class ListPresenter extends MvpBasePresenter<ListContract.View> implements ListContract.Presenter {

    @Repository
    @Inject
    MoviesDataSource moviesDataSource;

    @Inject
    ListPresenter() {
    }

    @Override
    public void getPopularMovies() {
        moviesDataSource.getPopularMovies(new MoviesDataSource.GetPopularMoviesCallback() {
            @Override
            public void onPopularMoviesDataReceived(final PagedMovies pagedMovies) {
                ifViewAttached(new ViewAction<ListContract.View>() {
                    @Override
                    public void run(@NonNull ListContract.View view) {
                        view.displayMovies(pagedMovies);
                    }
                });
            }

            @Override
            public void onPopularMoviesDataNotAvailable() {
                ifViewAttached(new ViewAction<ListContract.View>() {
                    @Override
                    public void run(@NonNull ListContract.View view) {
                        view.displayGettingPopuparMoviesError();
                    }
                });
            }
        });
    }
}
