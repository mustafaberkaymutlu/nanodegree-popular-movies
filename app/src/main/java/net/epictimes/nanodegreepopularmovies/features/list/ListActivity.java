package net.epictimes.nanodegreepopularmovies.features.list;

import android.os.Bundle;
import android.support.annotation.NonNull;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;
import net.epictimes.nanodegreepopularmovies.features.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ListActivity extends BaseActivity<ListContract.View, ListContract.Presenter>
        implements ListContract.View {

    @Inject
    ListContract.Presenter listPresenter;

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

        presenter.getPopularMovies();
    }

    @Override
    public void displayMovies(PagedMovies pagedMovies) {
        // TODO display movies
    }

    @Override
    public void displayGettingPopuparMoviesError() {
        // TODO display error
    }
}
