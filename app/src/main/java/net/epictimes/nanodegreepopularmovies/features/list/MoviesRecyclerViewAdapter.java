package net.epictimes.nanodegreepopularmovies.features.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.epictimes.nanodegreepopularmovies.data.model.Movie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mustafa Berkay Mutlu on 4.03.2018.
 */

public class MoviesRecyclerViewAdapter extends RecyclerView.Adapter<MovieViewHolder> {
    private final List<Movie> movies = new ArrayList<>();

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        final View rootView = inflater.inflate(MovieViewHolder.LAYOUT_ID, parent, false);
        return new MovieViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
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

}
