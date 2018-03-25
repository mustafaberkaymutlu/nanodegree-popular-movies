package net.epictimes.nanodegreepopularmovies.data.remote.response;

import com.google.gson.annotations.SerializedName;

import net.epictimes.nanodegreepopularmovies.data.model.Video;

import java.util.List;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public class GetVideosResponse {

    @SerializedName("id")
    private int id;

    @SerializedName("results")
    private List<Video> videos;

    public int getId() {
        return id;
    }

    public List<Video> getVideos() {
        return videos;
    }
}
