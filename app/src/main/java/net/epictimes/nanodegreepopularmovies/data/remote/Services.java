package net.epictimes.nanodegreepopularmovies.data.remote;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;
import net.epictimes.nanodegreepopularmovies.data.model.PagedReviews;
import net.epictimes.nanodegreepopularmovies.data.remote.response.GetVideosResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

interface Services {

    @GET("/3/movie/popular")
    Call<PagedMovies> getPopularMovies(@Query("page") int page);

    @GET("/3/movie/top_rated")
    Call<PagedMovies> getTopRatedMovies(@Query("page") int page);

    @GET("/3/movie/{movie-id}")
    Call<Movie> getMovie(@Path("movie-id") int movieId);

    @GET("/3/movie/{movie-id}/videos")
    Call<GetVideosResponse> getVideos(@Path("movie-id") int movieId);

    @GET("/3/movie/{movie-id}/reviews")
    Call<PagedReviews> getReviews(@Path("movie-id") int movieId,
                                  @Query("page") int page);

}
