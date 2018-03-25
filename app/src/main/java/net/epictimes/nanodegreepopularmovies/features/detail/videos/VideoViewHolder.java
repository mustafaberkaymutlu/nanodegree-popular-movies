package net.epictimes.nanodegreepopularmovies.features.detail.videos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.Video;
import net.epictimes.nanodegreepopularmovies.util.ViewHolderClickListener;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */
class VideoViewHolder extends RecyclerView.ViewHolder {
    static final int LAYOUT_ID = R.layout.video_item;

    private final TextView textViewName;
    private final TextView textViewType;

    VideoViewHolder(View itemView, ViewHolderClickListener clickListener) {
        super(itemView);

        textViewName = itemView.findViewById(R.id.textViewName);
        textViewType = itemView.findViewById(R.id.textViewType);

        itemView.setOnClickListener(v -> clickListener.onItemClicked(getAdapterPosition()));
    }

    void bindTo(Video video) {
        textViewName.setText(video.getName());
        textViewType.setText(video.getType());
    }
}
