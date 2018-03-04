package net.epictimes.nanodegreepopularmovies.features.list;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;

/**
 * Created by Mustafa Berkay Mutlu on 4.03.2018.
 */

class MovieViewHolder extends ViewHolder {
    public static final int LAYOUT_ID = R.layout.movie_item;

    private final ImageView imageViewPoster;
    private final TextView textViewTitle;

    MovieViewHolder(View itemView) {
        super(itemView);

        imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);
    }

    void bindTo(Movie movie) {
        textViewTitle.setText(movie.getTitle());
    }
}
