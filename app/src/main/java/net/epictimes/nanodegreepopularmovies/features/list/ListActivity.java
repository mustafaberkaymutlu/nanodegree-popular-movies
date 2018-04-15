package net.epictimes.nanodegreepopularmovies.features.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;
import net.epictimes.nanodegreepopularmovies.features.BaseActivity;
import net.epictimes.nanodegreepopularmovies.features.detail.DetailActivity;
import net.epictimes.nanodegreepopularmovies.util.EndlessRecyclerViewScrollListener;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ListActivity extends BaseActivity<ListContract.View, ListContract.Presenter>
        implements ListContract.View {

    @Inject
    ListContract.Presenter listPresenter;

    private MoviesRecyclerViewAdapter recyclerViewAdapter;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;
    private SwipeRefreshLayout swipeRefreshMovies;

    @NonNull
    @Override
    public ListContract.Presenter createPresenter() {
        return listPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshMovies = findViewById(R.id.swipeRefreshMovies);
        swipeRefreshMovies.setOnRefreshListener(() -> presenter.userRefreshed());

        final Spinner spinnerSortCriteria = findViewById(R.id.spinnerSortCriteria);

        final ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.sort_criteria, R.layout.spinner_item_sort_criteria);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSortCriteria.setAdapter(spinnerAdapter);

        spinnerSortCriteria.setOnItemSelectedListener(new MyOnItemSelectedListener());

        final RecyclerView recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        final int spanCount = getResources().getInteger(R.integer.list_span_count);
        final GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, spanCount);

        recyclerViewAdapter = new MoviesRecyclerViewAdapter();
        recyclerViewAdapter.setMovieClickListener(movie -> presenter.userClickedMovie(movie));

        endlessRecyclerViewScrollListener =
                new EndlessRecyclerViewScrollListener(gridLayoutManager) {
                    @Override
                    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                        presenter.loadMore();
                    }
                };

        recyclerViewMovies.setLayoutManager(gridLayoutManager);
        recyclerViewMovies.addOnScrollListener(endlessRecyclerViewScrollListener);
        recyclerViewMovies.setAdapter(recyclerViewAdapter);
        recyclerViewMovies.setHasFixedSize(true);

        presenter.getMovies();
    }

    @Override
    public void displayMovies(PagedMovies pagedMovies) {
        recyclerViewAdapter.addAll(pagedMovies.getResults());
    }

    @Override
    public void clearMovies() {
        recyclerViewAdapter.clear();
        endlessRecyclerViewScrollListener.resetState();
    }

    @Override
    public void showLoading() {
        swipeRefreshMovies.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshMovies.setRefreshing(false);
    }

    @Override
    public void displayGettingPopularMoviesError(SortCriteria sortCriteria) {
        switch (sortCriteria) {
            case POPULAR:
                Toast.makeText(this, R.string.error_getting_popular_movies, Toast.LENGTH_SHORT).show();
                break;
            case TOP_RATED:
                Toast.makeText(this, R.string.error_getting_top_rated_movies, Toast.LENGTH_SHORT).show();
                break;
            case FAVORITES:
                Toast.makeText(this, R.string.error_getting_favorite_movies, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void goToMovieDetail(int movieId) {
        final Intent detailIntent = DetailActivity.newIntent(this, movieId);
        startActivity(detailIntent);
    }

    private class MyOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:
                    presenter.switchSortCriteria(SortCriteria.POPULAR);
                    break;
                case 1:
                    presenter.switchSortCriteria(SortCriteria.TOP_RATED);
                    break;
                case 2:
                    presenter.switchSortCriteria(SortCriteria.FAVORITES);
                    break;
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
            // no-op
        }
    }
}
