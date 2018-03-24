package net.epictimes.nanodegreepopularmovies.features.detail.reviews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.util.Preconditions;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public class ReviewsFragment extends MvpFragment<ReviewsContract.View, ReviewsContract.Presenter>
        implements ReviewsContract.View {
    private static final String KEY_MOVIE_ID = "movie_id";

    @Inject
    ReviewsContract.Presenter reviewsPresenter;

    private int movieId;

    public static ReviewsFragment newInstance(int movieId) {
        final ReviewsFragment fragment = new ReviewsFragment();
        final Bundle args = new Bundle();
        args.putInt(KEY_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public ReviewsContract.Presenter createPresenter() {
        return reviewsPresenter;
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments);
        movieId = arguments.getInt(KEY_MOVIE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.getReviews(movieId);
    }

    @Override
    public void displayReviews() {
        // TODO display reviews
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), R.string.error_displaying_movie_reviews, Toast.LENGTH_SHORT).show();
    }
}
