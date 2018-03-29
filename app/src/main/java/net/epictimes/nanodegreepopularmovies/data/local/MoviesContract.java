package net.epictimes.nanodegreepopularmovies.data.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Mustafa Berkay Mutlu on 29.03.2018.
 */
class MoviesContract {

    static final String CONTENT_AUTHORITY = "net.epictimes.nanodegreepopularmovies";

    static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    static final class MovieEntry implements BaseColumns {
        static final String TABLE_MOVIES = "movies";

        static final String _ID = "_id";
        static final String COLUMN_TITLE = "title";
        static final String COLUMN_OVERVIEW = "overview";
        static final String COLUMN_RELEASE_DATE = "release_date";
        static final String COLUMN_POSTER_PATH = "poster_path";
        static final String COLUMN_BACKDROP_PATH = "backdrop_path";
        static final String COLUMN_VOTE_AVERAGE = "vote_average";

        // create content uri
        static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(TABLE_MOVIES).build();

        // create cursor of base type directory for multiple entries
        static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIES;

        // create cursor of base type item for single entry
        static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + TABLE_MOVIES;

        // for building URIs on insertion
        static Uri buildMoviesUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

}
