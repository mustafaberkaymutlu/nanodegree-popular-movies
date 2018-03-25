package net.epictimes.nanodegreepopularmovies.features.detail.videos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.epictimes.nanodegreepopularmovies.data.model.Video;
import net.epictimes.nanodegreepopularmovies.util.ViewHolderClickListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Mustafa Berkay Mutlu on 25.03.2018.
 */

class VideosRecyclerAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    private final List<Video> videos = new ArrayList<>();

    @Nullable
    private OnClickListener onClickListener;

    interface OnClickListener {

        void onVideoClicked(Video video);

    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(VideoViewHolder.LAYOUT_ID, parent, false);

        final ViewHolderClickListener clickListener = adapterPosition -> {
            if (onClickListener != null) {
                onClickListener.onVideoClicked(videos.get(adapterPosition));
            }
        };

        return new VideoViewHolder(view, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        final Video video = videos.get(position);
        holder.bindTo(video);
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    void addAll(Collection<Video> newVideos) {
        videos.clear();
        videos.addAll(newVideos);
        notifyDataSetChanged();
    }

    void setOnClickListener(@Nullable OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
