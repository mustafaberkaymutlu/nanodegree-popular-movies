package net.epictimes.nanodegreepopularmovies.features.detail.videos;

import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import net.epictimes.nanodegreepopularmovies.data.model.Video;

import java.util.List;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public interface VideosContract {

    interface View extends MvpView {

        void displayVideos(List<Video> videos);

        void displayError();

        void goToVideo(Video video);
    }

    interface Presenter extends MvpPresenter<View> {

        void getTrailers(int movieId);

        void onVideoClicked(Video video);
    }

}
