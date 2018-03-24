package net.epictimes.nanodegreepopularmovies.features.detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.ColorInt;
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

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final TabLayout tabLayout = findViewById(R.id.tabLayout);
        final ViewPager viewPager = findViewById(R.id.viewPager);
        imageViewBackdrop = findViewById(R.id.imageViewBackdrop);
        imageViewPoster = findViewById(R.id.imageViewPoster);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewReleaseDate = findViewById(R.id.textViewReleaseDate);
        textViewVoteAverage = findViewById(R.id.textViewVoteAverage);

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

        // TODO set favorite status of movie
        final MenuItem item = menu.findItem(R.id.action_favorite);
        item.setIcon(R.drawable.ic_favorite_border_white_24px);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite: {
                Toast.makeText(DetailActivity.this, "Not implemented yet", Toast.LENGTH_SHORT).show();
                // TODO implement adding/removing favorites
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
