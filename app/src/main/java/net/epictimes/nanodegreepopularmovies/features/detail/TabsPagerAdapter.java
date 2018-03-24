package net.epictimes.nanodegreepopularmovies.features.detail;

import android.support.annotation.DrawableRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.features.detail.overview.OverviewFragment;
import net.epictimes.nanodegreepopularmovies.features.detail.reviews.ReviewsFragment;
import net.epictimes.nanodegreepopularmovies.features.detail.trailers.TrailersFragment;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

class TabsPagerAdapter extends FragmentStatePagerAdapter {
    private static final int TAB_COUNT = 3;

    private final int movieId;

    TabsPagerAdapter(FragmentManager fm, int movieId) {
        super(fm);

        this.movieId = movieId;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OverviewFragment.newInstance(movieId);
            case 1:
                return TrailersFragment.newInstance(movieId);
            case 2:
                return ReviewsFragment.newInstance(movieId);
            default:
                throw new IllegalStateException("Unknown tab position");
        }
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @DrawableRes
    static int getIconRes(int position) {
        switch (position) {
            case 0:
                return R.drawable.ic_information_variant_24px;
            case 1:
                return R.drawable.ic_movie_white_24px;
            case 2:
                return R.drawable.ic_rate_review_white_24px;
            default:
                throw new IllegalStateException("Unknown tab position");
        }
    }
}
