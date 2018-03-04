package net.epictimes.nanodegreepopularmovies.features.list;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.data.remote.Endpoint;
import net.epictimes.nanodegreepopularmovies.util.GlideApp;
import net.epictimes.nanodegreepopularmovies.util.ItemClickListener;

/**
 * Created by Mustafa Berkay Mutlu on 4.03.2018.
 */

class MovieViewHolder extends ViewHolder {
    static final int LAYOUT_ID = R.layout.movie_item;

    private final ImageView imageViewPoster;
    private final TextView textViewTitle;

    MovieViewHolder(View itemView, ItemClickListener itemClickListener) {
        super(itemView);

        imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        textViewTitle = itemView.findViewById(R.id.textViewTitle);

        itemView.setOnClickListener(v -> itemClickListener.onItemClicked(getAdapterPosition()));
    }

    void bindTo(Movie movie) {
        textViewTitle.setText(movie.getTitle());

        GlideApp.with(imageViewPoster.getContext())
                .load(Endpoint.POSTER_BASE + movie.getPosterPath())
                .into(imageViewPoster);
    }
}
