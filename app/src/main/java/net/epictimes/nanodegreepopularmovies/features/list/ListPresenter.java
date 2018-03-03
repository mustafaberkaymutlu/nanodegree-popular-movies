package net.epictimes.nanodegreepopularmovies.features.list;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public class ListPresenter extends MvpBasePresenter<ListContract.View> implements ListContract.Presenter {

    @Inject
    public ListPresenter() {
    }
}
