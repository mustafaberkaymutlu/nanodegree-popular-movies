package net.epictimes.nanodegreepopularmovies.features.detail.videos;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import net.epictimes.nanodegreepopularmovies.data.VideosDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.Video;
import net.epictimes.nanodegreepopularmovies.di.qualifier.Repository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public class VideosPresenter extends MvpBasePresenter<VideosContract.View>
        implements VideosContract.Presenter {

    @Repository
    @Inject
    VideosDataSource repository;

    @Inject
    VideosPresenter() {
    }

    @Override
    public void getTrailers(int movieId) {
        repository.getVideos(movieId, new VideosDataSource.GetVideosCallback() {
            @Override
            public void onVideosReceived(List<Video> videos) {
                ifViewAttached(view -> view.displayVideos(videos));
            }

            @Override
            public void onVideosNotAvailable() {
                ifViewAttached(VideosContract.View::displayError);
            }
        });
    }

    @Override
    public void onVideoClicked(Video video) {
        ifViewAttached(view -> view.goToVideo(video));
    }
}
