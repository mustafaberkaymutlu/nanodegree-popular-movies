package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.data.model.Video;

import java.util.List;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public interface VideosDataSource {

    void getVideos(int movieId, GetVideosCallback callback);

    interface GetVideosCallback {

        void onVideosReceived(List<Video> videos);

        void onVideosNotAvailable();

    }

}
