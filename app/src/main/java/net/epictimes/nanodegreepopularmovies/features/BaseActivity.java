package net.epictimes.nanodegreepopularmovies.features;

import com.hannesdorfmann.mosby3.mvp.MvpActivity;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {
}
