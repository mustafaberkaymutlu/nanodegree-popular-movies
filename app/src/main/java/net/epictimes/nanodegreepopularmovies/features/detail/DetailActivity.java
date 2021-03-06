package net.epictimes.nanodegreepopularmovies.features.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
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
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Mustafa Berkay Mutlu on 4.03.2018.
 */

public class DetailActivity extends BaseActivity<DetailContract.View, DetailContract.Presenter>
        implements DetailContract.View, HasSupportFragmentInjector {
    private static final String KEY_MOVIE_ID = "movie_id";

    private boolean isFavorite;

    public static Intent newIntent(@NonNull Context context, int movieId) {
        final Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(KEY_MOVIE_ID, movieId);
        return intent;
    }

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    DetailContract.Presenter detailPresenter;

    private Toolbar toolbar;
    private ImageView imageViewPoster;
    private ImageView imageViewBackdrop;
    private TextView textViewTitle;
    private TextView textViewReleaseDate;
    private RatingBar ratingBarVoteAverage;

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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        imageViewBackdrop = findViewById(R.id.imageViewBackdrop);
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewReleaseDate = findViewById(R.id.textViewReleaseDate);
        ratingBarVoteAverage = findViewById(R.id.ratingBarVoteAverage);

        final int movieId = getIntent().getIntExtra(KEY_MOVIE_ID, -1);

        viewPager.setAdapter(new TabsPagerAdapter(getSupportFragmentManager(), movieId));
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setOffscreenPageLimit(tabLayout.getTabCount() - 1);

        for (int i = 0; i < tabLayout.getTabCount(); i++) {
            tabLayout.getTabAt(i).setIcon(TabsPagerAdapter.getIconRes(i));
        }

        presenter.getMovie(movieId);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        @DrawableRes final int iconRes = isFavorite
                ? R.drawable.ic_favorite_white_24px
                : R.drawable.ic_favorite_border_white_24px;

        final MenuItem menuItemFavorite = menu.findItem(R.id.action_favorite);
        menuItemFavorite.setIcon(iconRes);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite: {
                presenter.switchFavoriteStatus();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void displayMovie(Movie movie) {
        GlideApp.with(this)
                .asBitmap()
                .load(Endpoint.POSTER_BASE_BIG + movie.getPosterPath())
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e,
                                                Object model,
                                                Target<Bitmap> target,
                                                boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource,
                                                   Object model,
                                                   Target<Bitmap> target,
                                                   DataSource dataSource,
                                                   boolean isFirstResource) {
                        createPaletteAsync(resource);
                        return false;
                    }
                })
                .into(imageViewPoster);

        GlideApp.with(this)
                .load(Endpoint.POSTER_BASE_BIG + movie.getBackdropPath())
                .into(imageViewBackdrop);

        textViewTitle.setText(movie.getTitle());
        textViewReleaseDate.setText(movie.getReleaseDate());
        ratingBarVoteAverage.setRating((float) movie.getVoteAverage());
    }

    @Override
    public void displayMovieError() {
        Toast.makeText(this, R.string.error_displaying_movie, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayAddedToFavorites() {
        displayFavoriteStatus(true);
        Toast.makeText(this, R.string.movie_added_to_favorites, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayRemovedFromFavorites() {
        displayFavoriteStatus(false);
        Toast.makeText(this, R.string.movie_removed_from_favorites, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayFavoriteStatus(boolean isFavorite) {
        this.isFavorite = isFavorite;
        invalidateOptionsMenu();
    }

    private void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(p -> {
            @ColorInt final int scrimColor = p.getVibrantColor(ContextCompat.getColor(this,
                    R.color.colorPrimary));

            @ColorInt final int statusBarColor = p.getDarkVibrantColor(ContextCompat.getColor(this,
                    R.color.colorPrimaryDark));

            toolbar.setBackgroundColor(scrimColor);
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

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
