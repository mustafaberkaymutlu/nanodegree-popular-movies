package net.epictimes.nanodegreepopularmovies.features.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.remote.Endpoint;
import net.epictimes.nanodegreepopularmovies.features.BaseActivity;
import net.epictimes.nanodegreepopularmovies.util.GlideApp;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by Mustafa Berkay Mutlu on 4.03.2018.
 */

public class DetailActivity extends BaseActivity<DetailContract.View, DetailContract.Presenter>
        implements DetailContract.View {
    private static final String KEY_MOVIE_ID = "movie_id";

    public static Intent newIntent(@NonNull Context context, int movieId) {
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_MOVIE_ID, movieId);
        return intent;
    }

    @Inject
    DetailContract.Presenter detailPresenter;

    private ImageView imageViewPoster;
    private TextView textViewTitle;
    private TextView textViewOverview;
    private TextView textViewReleaseDate;
    private TextView textViewVoteAverage;

    @NonNull
    @Override
    public DetailContract.Presenter createPresenter() {
        return detailPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final int movieId = getIntent().getIntExtra(KEY_MOVIE_ID, -1);

        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewOverview = findViewById(R.id.textViewOverview);
        textViewReleaseDate = findViewById(R.id.textViewReleaseDate);
        textViewVoteAverage = findViewById(R.id.textViewVoteAverage);

        presenter.getMovie(movieId);
    }

    @Override
    public void displayMovie(Movie movie) {
        GlideApp.with(this)
                .load(Endpoint.POSTER_BASE + movie.getPosterPath())
                .into(imageViewPoster);

        textViewTitle.setText(movie.getTitle());
        textViewOverview.setText(movie.getOverview());
        textViewReleaseDate.setText(movie.getReleaseDate());
        textViewVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
    }

    @Override
    public void displayMovieError() {
        Toast.makeText(this, R.string.error_displaying_movie, Toast.LENGTH_SHORT).show();
    }
}
