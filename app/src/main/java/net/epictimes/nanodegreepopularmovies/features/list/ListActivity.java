package net.epictimes.nanodegreepopularmovies.features.list;

import android.os.Bundle;
import android.support.annotation.NonNull;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.features.BaseActivity;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class ListActivity extends BaseActivity<ListContract.View, ListContract.Presenter> {

    @Inject
    ListContract.Presenter presenter;

    @NonNull
    @Override
    public ListContract.Presenter createPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
    }
}
