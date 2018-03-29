package net.epictimes.nanodegreepopularmovies.features.detail.reviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.epictimes.nanodegreepopularmovies.data.model.Review;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

class ReviewsRecyclerAdapter extends RecyclerView.Adapter<ReviewsViewHolder> {
    private final List<Review> reviews = new ArrayList<>();

    @NonNull
    @Override
    public ReviewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(ReviewsViewHolder.LAYOUT_ID, parent, false);
        return new ReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsViewHolder holder, int position) {
        final Review review = reviews.get(position);
        holder.bindTo(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    void addAll(Collection<Review> newReviews) {
        final int previousSize = reviews.size();
        reviews.addAll(newReviews);
        notifyItemRangeInserted(previousSize, newReviews.size());
    }
}
