package net.epictimes.nanodegreepopularmovies.data.local;

import android.content.ContentResolver;
import android.database.Cursor;

import net.epictimes.nanodegreepopularmovies.data.MoviesDataSource;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.model.PagedMovies;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Mustafa Berkay Mutlu on 29.03.2018.
 */
public class MoviesLocalDataSource implements MoviesDataSource {

    @Inject
    ContentResolver contentResolver;

    @Inject
    MoviesLocalDataSource() {
    }

    @Override
    public void getPopularMovies(int page, GetMoviesCallback callback) {
        throw new UnsupportedOperationException("Querying using local is not supported. ");
    }

    @Override
    public void getTopRatedMovies(int page, GetMoviesCallback callback) {
        throw new UnsupportedOperationException("Querying using local is not supported. ");
    }

    @Override
    public void getFavoriteMovies(GetMoviesCallback callback) {
        final Cursor favoritesCursor = contentResolver.query(MoviesContract.MovieEntry.CONTENT_URI,
                null, null, null, null);

        if (favoritesCursor == null) {
            callback.onMoviesNotAvailable();
            return;
        }

        final MovieCursorWrapper cursorWrapper = new MovieCursorWrapper(favoritesCursor);

        final List<Movie> movies = new ArrayList<>();
        for (cursorWrapper.moveToFirst(); !cursorWrapper.isAfterLast(); cursorWrapper.moveToNext()) {
            movies.add(cursorWrapper.getMovie());
        }

        favoritesCursor.close();

        callback.onMoviesReceived(new PagedMovies(movies));
    }

    @Override
    public void getMovieById(int movieId, GetMovieCallback callback) {
        throw new UnsupportedOperationException("Querying using local is not supported. ");
    }

    @Override
    public void addToFavorites(Movie movie) {
        contentResolver.insert(MoviesContract.MovieEntry.CONTENT_URI,
                MovieCursorWrapper.getContentValues(movie));
    }

    @Override
    public void removeFromFavorites(int movieId) {
        final String selectionClause = MoviesContract.MovieEntry._ID + " == ?";
        final String[] selectionArgs = {String.valueOf(movieId)};

        contentResolver.delete(MoviesContract.MovieEntry.CONTENT_URI,
                selectionClause, selectionArgs);
    }

    @Override
    public void isMovieFavorite(int movieId, IsFavoriteCallback callback) {
        final String selectionClause = MoviesContract.MovieEntry._ID + " == ?";
        final String[] selectionArgs = {String.valueOf(movieId)};

        final Cursor favoritesCursor = contentResolver.query(MoviesContract.MovieEntry.CONTENT_URI,
                null, selectionClause, selectionArgs, null);

        if (favoritesCursor == null) {
            callback.onIsFavoriteReceived(false);
        } else {
            favoritesCursor.close();

            final boolean isEmpty = favoritesCursor.getCount() == 0;
            callback.onIsFavoriteReceived(!isEmpty);
        }
    }

}
