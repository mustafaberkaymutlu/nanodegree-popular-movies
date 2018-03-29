package net.epictimes.nanodegreepopularmovies.data.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.support.annotation.NonNull;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;

/**
 * Created by Mustafa Berkay Mutlu on 29.03.2018.
 */
class MovieCursorWrapper extends CursorWrapper {

    MovieCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    @NonNull
    Movie getMovie() {
        final int id = getInt(getColumnIndex(MoviesContract.MovieEntry._ID));
        final String title = getString(getColumnIndex(MoviesContract.MovieEntry.COLUMN_TITLE));
        final String overview = getString(getColumnIndex(MoviesContract.MovieEntry.COLUMN_OVERVIEW));
        final String releaseDate = getString(getColumnIndex(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE));
        final String posterPath = getString(getColumnIndex(MoviesContract.MovieEntry.COLUMN_POSTER_PATH));
        final String backdropPath = getString(getColumnIndex(MoviesContract.MovieEntry.COLUMN_BACKDROP_PATH));
        final double voteAverage = getDouble(getColumnIndex(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE));

        final Movie movie = new Movie();

        movie.setId(id);
        movie.setTitle(title);
        movie.setOverview(overview);
        movie.setReleaseDate(releaseDate);
        movie.setPosterPath(posterPath);
        movie.setBackdropPath(backdropPath);
        movie.setVoteAverage(voteAverage);

        return movie;
    }

    static ContentValues getContentValues(Movie movie) {
        final ContentValues values = new ContentValues();

        values.put(MoviesContract.MovieEntry._ID, movie.getId());
        values.put(MoviesContract.MovieEntry.COLUMN_TITLE, movie.getTitle());
        values.put(MoviesContract.MovieEntry.COLUMN_OVERVIEW, movie.getOverview());
        values.put(MoviesContract.MovieEntry.COLUMN_RELEASE_DATE, movie.getReleaseDate());
        values.put(MoviesContract.MovieEntry.COLUMN_POSTER_PATH, movie.getPosterPath());
        values.put(MoviesContract.MovieEntry.COLUMN_BACKDROP_PATH, movie.getBackdropPath());
        values.put(MoviesContract.MovieEntry.COLUMN_VOTE_AVERAGE, movie.getVoteAverage());

        return values;
    }
}
