package net.epictimes.nanodegreepopularmovies.data;

import net.epictimes.nanodegreepopularmovies.di.qualifier.RemoteDataSource;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public class VideosRepository implements VideosDataSource {

    @RemoteDataSource
    @Inject
    VideosDataSource remoteDataSource;

    @Inject
    VideosRepository() {
    }

    @Override
    public void getVideos(int movieId, GetVideosCallback callback) {
        remoteDataSource.getVideos(movieId, callback);
    }
}
