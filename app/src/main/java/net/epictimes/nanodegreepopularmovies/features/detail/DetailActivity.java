package net.epictimes.nanodegreepopularmovies.features.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

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

    private CollapsingToolbarLayout collapsingToolbarLayout;
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

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewOverview = findViewById(R.id.textViewOverview);
        textViewReleaseDate = findViewById(R.id.textViewReleaseDate);
        textViewVoteAverage = findViewById(R.id.textViewVoteAverage);

        final int movieId = getIntent().getIntExtra(KEY_MOVIE_ID, -1);

        presenter.getMovie(movieId);
    }

    @Override
    public void displayMovie(Movie movie) {
        GlideApp.with(this)
                .asBitmap()
                .load(Endpoint.POSTER_BASE_BIG + movie.getPosterPath())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        createPaletteAsync(resource);
                        return false;
                    }
                })
                .into(imageViewPoster);

        collapsingToolbarLayout.setTitle(movie.getTitle());

        textViewTitle.setText(movie.getTitle());
        textViewOverview.setText(movie.getOverview());
        textViewReleaseDate.setText(movie.getReleaseDate());
        textViewVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
    }

    @Override
    public void displayMovieError() {
        Toast.makeText(this, R.string.error_displaying_movie, Toast.LENGTH_SHORT).show();
    }

    private void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(p -> {
            @ColorInt final int scrimColor = p.getVibrantColor(ContextCompat.getColor(this,
                    R.color.colorPrimary));

            @ColorInt final int statusBarColor = p.getDarkVibrantColor(ContextCompat.getColor(this,
                    R.color.colorPrimaryDark));

            collapsingToolbarLayout.setContentScrimColor(scrimColor);
            setStatusBarColor(statusBarColor);
        });
    }

    private void setStatusBarColor(@ColorInt int color) {
        final Window window = getWindow();

        if (window == null) {
            return;
        }

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(color);
    }
}
