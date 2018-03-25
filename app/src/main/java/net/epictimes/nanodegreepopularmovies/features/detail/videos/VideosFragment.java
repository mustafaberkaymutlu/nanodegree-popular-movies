package net.epictimes.nanodegreepopularmovies.features.detail.videos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hannesdorfmann.mosby3.mvp.MvpFragment;

import net.epictimes.nanodegreepopularmovies.R;
import net.epictimes.nanodegreepopularmovies.data.model.Video;
import net.epictimes.nanodegreepopularmovies.util.Preconditions;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;

/**
 * Created by Mustafa Berkay Mutlu on 24.03.2018.
 */

public class VideosFragment extends MvpFragment<VideosContract.View, VideosContract.Presenter>
        implements VideosContract.View {
    private static final String KEY_MOVIE_ID = "movie_id";

    @Inject
    VideosContract.Presenter trailersPresenter;

    private int movieId;

    private VideosRecyclerAdapter adapter;

    public static VideosFragment newInstance(int movieId) {
        final VideosFragment fragment = new VideosFragment();
        final Bundle args = new Bundle();
        args.putInt(KEY_MOVIE_ID, movieId);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public VideosContract.Presenter createPresenter() {
        return trailersPresenter;
    }

    @Override
    public void onAttach(Activity activity) {
        AndroidSupportInjection.inject(this);
        super.onAttach(activity);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle arguments = getArguments();
        Preconditions.checkNotNull(arguments);
        movieId = arguments.getInt(KEY_MOVIE_ID);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_videos, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final RecyclerView recyclerViewVideos = view.findViewById(R.id.recyclerViewVideos);

        final Context context = getContext();
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        adapter = new VideosRecyclerAdapter();

        final DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context,
                linearLayoutManager.getOrientation());

        dividerItemDecoration.setDrawable(context.getDrawable(R.drawable.videos_divider));

        recyclerViewVideos.addItemDecoration(dividerItemDecoration);
        recyclerViewVideos.setLayoutManager(linearLayoutManager);
        recyclerViewVideos.setAdapter(adapter);

        adapter.setOnClickListener(video -> presenter.onVideoClicked(video));

        presenter.getTrailers(movieId);
    }

    @Override
    public void displayVideos(List<Video> videos) {
        adapter.addAll(videos);
    }

    @Override
    public void displayError() {
        Toast.makeText(getContext(), R.string.error_displaying_movie_trailers, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goToVideo(Video video) {
        final Uri uri = Uri.parse(video.getYoutubeUrl());
        final Intent videoIntent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(videoIntent);
    }
}
