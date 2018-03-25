package net.epictimes.nanodegreepopularmovies.features.detail.reviews;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.Review;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

class ReviewsViewHolder extends RecyclerView.ViewHolder {
    static final int LAYOUT_ID = R.layout.review_item;

    private final TextView textViewAuthor;
    private final TextView textViewReview;

    ReviewsViewHolder(View itemView) {
        super(itemView);

        textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
        textViewReview = itemView.findViewById(R.id.textViewReview);
    }

    void bindTo(Review review) {
        textViewAuthor.setText(review.getAuthor());
        textViewReview.setText(review.getContent());
    }
}
