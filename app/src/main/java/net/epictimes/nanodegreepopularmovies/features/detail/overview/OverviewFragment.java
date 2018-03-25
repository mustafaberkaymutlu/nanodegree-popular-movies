package net.epictimes.nanodegreepopularmovies.features.detail.overview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.util.Preconditions;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public class OverviewFragment extends MvpFragment<OverviewContract.View, OverviewContract.Presenter>
        implements OverviewContract.View {
    private static final String KEY_MOVIE_ID = "movie_id";

    @Inject
    OverviewContract.Presenter overviewPresenter;

    private int movieId;

    private TextView textViewOverview;

    public static OverviewFragment newInstance(int movieId) {
        final OverviewFragment fragment = new OverviewFragment();
        final Bundle args = new Bundle();
        args.putInt(KEY_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public OverviewContract.Presenter createPresenter() {
        return overviewPresenter;
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
        return inflater.inflate(R.layout.fragment_overview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewOverview = view.findViewById(R.id.textViewOverview);

        presenter.getMovieOverview(movieId);
    }

    @Override
    public void displayMovieOverview(Movie movie) {
        textViewOverview.setText(movie.getOverview());
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), R.string.error_displaying_movie_overview, Toast.LENGTH_SHORT).show();
    }
}
