package net.epictimes.nanodegreepopularmovies.features.list;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;
import net.epictimes.nanodegreepopularmovies.util.ItemClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mustafa Berkay Mutlu on 4.03.2018.
 */

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MovieViewHolder>
        implements ItemClickListener {
    private final List<Movie> movies = new ArrayList<>();

    @Nullable
    private MovieClickListener movieClickListener;

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View rootView = inflater.inflate(MovieViewHolder.LAYOUT_ID, parent, false);
        return new MovieViewHolder(rootView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        final Movie movie = movies.get(position);
        holder.bindTo(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    void addAll(@NonNull Collection<Movie> newMovies) {
        final int previousSize = movies.size();
        movies.addAll(newMovies);
        notifyItemRangeInserted(previousSize, newMovies.size());
    }

    void clear() {
        final int previousSize = movies.size();
        movies.clear();
        notifyItemRangeRemoved(0, previousSize);
    }

    void setMovieClickListener(@Nullable MovieClickListener movieClickListener) {
        this.movieClickListener = movieClickListener;
    }

    @Override
    public void onItemClicked(int adapterPosition) {
        if (movieClickListener != null) {
            movieClickListener.onMovieClicked(movies.get(adapterPosition));
        }
    }

    public interface MovieClickListener {

        void onMovieClicked(Movie movie);

    }

}
