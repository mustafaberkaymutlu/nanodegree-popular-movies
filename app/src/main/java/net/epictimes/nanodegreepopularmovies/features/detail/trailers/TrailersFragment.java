package net.epictimes.nanodegreepopularmovies.features.detail.trailers;

import android.app.Activity;
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

public class TrailersFragment extends MvpFragment<TrailersContract.View, TrailersContract.Presenter>
        implements TrailersContract.View {
    private static final String KEY_MOVIE_ID = "movie_id";

    @Inject
    TrailersContract.Presenter trailersPresenter;

    private int movieId;

    public static TrailersFragment newInstance(int movieId) {
        final TrailersFragment fragment = new TrailersFragment();
        final Bundle args = new Bundle();
        args.putInt(KEY_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public TrailersContract.Presenter createPresenter() {
        return trailersPresenter;
    }

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
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
        return inflater.inflate(R.layout.fragment_trailers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.getTrailers(movieId);
    }

    @Override
    public void displayTrailers() {
        // TODO display trailers
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), R.string.error_displaying_movie_trailers, Toast.LENGTH_SHORT).show();
    }
}
