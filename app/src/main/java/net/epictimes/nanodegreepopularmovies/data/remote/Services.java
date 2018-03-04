package net.epictimes.nanodegreepopularmovies.data.remote;

import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mustafa Berkay Mutlu on 3.03.2018.
 */

public interface Services {

    @GET("/3/movie/popular")
    Call<PagedMovies> getPopularMovies(@Query("page") int page);

}
