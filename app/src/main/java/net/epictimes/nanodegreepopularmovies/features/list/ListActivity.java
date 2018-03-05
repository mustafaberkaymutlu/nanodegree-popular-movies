package net.epictimes.nanodegreepopularmovies.features.list;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
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
    private static final int MOVIES_GRID_SPAN_COUNT = 2;

    @Inject
    ListContract.Presenter listPresenter;

    private MoviesRecyclerViewAdapter recyclerViewAdapter;
    private EndlessRecyclerViewScrollListener endlessRecyclerViewScrollListener;

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

        final RecyclerView recyclerViewMovies = findViewById(R.id.recyclerViewMovies);
        final GridLayoutManager gridLayoutManager =
                new GridLayoutManager(this, MOVIES_GRID_SPAN_COUNT);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_switch_popular:
                presenter.switchSortCriteria(SortCriteria.POPULAR);
                return true;
            case R.id.action_switch_top_rated:
                presenter.switchSortCriteria(SortCriteria.TOP_RATED);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
    public void displayGettingPopularMoviesError() {
        Toast.makeText(this, R.string.error_getting_popular_movies, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToMovieDetail(int movieId) {
        final Intent detailIntent = DetailActivity.newIntent(this, movieId);
        startActivity(detailIntent);
    }
}
