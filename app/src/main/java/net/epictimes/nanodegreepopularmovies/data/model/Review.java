package net.epictimes.nanodegreepopularmovies.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

public class Review {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    public String getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
