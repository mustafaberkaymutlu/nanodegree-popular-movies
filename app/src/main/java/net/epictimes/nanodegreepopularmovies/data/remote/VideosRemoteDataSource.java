package net.epictimes.nanodegreepopularmovies.data.remote;

import net.epictimes.nanodegreepopularmovies.data.VideosDataSource;
import net.epictimes.nanodegreepopularmovies.data.remote.response.GetVideosResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public class VideosRemoteDataSource implements VideosDataSource {

    @Inject
    Services services;

    @Inject
    VideosRemoteDataSource() {
    }

    @Override
    public void getVideos(int movieId, GetVideosCallback callback) {
        services.getVideos(movieId)
                .enqueue(new Callback<GetVideosResponse>() {
                    @Override
                    public void onResponse(Call<GetVideosResponse> call,
                                           Response<GetVideosResponse> response) {
                        final GetVideosResponse body = response.body();

                        if (response.isSuccessful() && body != null) {
                            callback.onVideosReceived(body.getVideos());
                        } else {
                            callback.onVideosNotAvailable();
                        }
                    }

                    @Override
                    public void onFailure(Call<GetVideosResponse> call, Throwable t) {
                        callback.onVideosNotAvailable();
                    }
                });
    }
}
