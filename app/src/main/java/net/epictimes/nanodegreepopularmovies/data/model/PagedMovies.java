package net.epictimes.nanodegreepopularmovies.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public class PagedMovies {

    @SerializedName("page")
    private int page;

    @SerializedName("results")
    private List<Movie> results;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    public PagedMovies(List<Movie> results) {
        this.page = 1;
        this.results = results;
        this.totalResults = results.size();
        this.totalPages = 1;
    }

    public int getPage() {
        return page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
