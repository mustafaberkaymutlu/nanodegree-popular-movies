package net.epictimes.nanodegreepopularmovies.features.detail.reviews;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public class ReviewsPresenter extends MvpBasePresenter<ReviewsContract.View>
        implements ReviewsContract.Presenter {

    @Repository
    @Inject
    MoviesDataSource repository;

    @Inject
    public ReviewsPresenter() {
    }

    @Override
    public void getReviews(int movieId) {
        // TODO get reviews
    }
}
