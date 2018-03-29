package net.epictimes.nanodegreepopularmovies.features.detail.reviews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.PagedReviews;
import net.epictimes.nanodegreepopularmovies.util.EndlessRecyclerViewScrollListener;
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

    private ReviewsRecyclerAdapter adapter;

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

        final Bundle arguments = Preconditions.checkNotNull(getArguments());
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

        RecyclerView recyclerViewReviews = view.findViewById(R.id.recyclerViewReviews);

        final Context context = getContext();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        adapter = new ReviewsRecyclerAdapter();

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,
                linearLayoutManager.getOrientation());

        dividerItemDecoration.setDrawable(context.getDrawable(R.drawable.videos_divider));

        final EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener =
                new EndlessRecyclerViewScrollListener(linearLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        presenter.loadMore();
                    }
                };

        recyclerViewReviews.addItemDecoration(dividerItemDecoration);
        recyclerViewReviews.setLayoutManager(linearLayoutManager);
        recyclerViewReviews.addOnScrollListener(endlessRecyclerViewScrollListener);
        recyclerViewReviews.setAdapter(adapter);

        presenter.getReviews(movieId);
    }

    @Override
    public void displayReviews(PagedReviews pagedReviews) {
        adapter.addAll(pagedReviews.getReviews());
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), R.string.error_displaying_movie_reviews, Toast.LENGTH_SHORT).show();
    }
}
