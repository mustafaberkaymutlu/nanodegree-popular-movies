package net.epictimes.nanodegreepopularmovies.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public class Video {

    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;

    @SerializedName("type")
    private String type;

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public String getType() {
        return type;
    }

    public String getYoutubeUrl() {
        return "https://www.youtube.com/watch?v=" + key;
    }
}
