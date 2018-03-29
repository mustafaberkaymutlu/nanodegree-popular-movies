package net.epictimes.nanodegreepopularmovies.util;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * Created by Mustafa Berkay Mutlu on 4.03.2018.
 */

public abstract class EndlessRecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private static final int VISIBLE_THRESHOLD = 5;
    private static final int STARTING_PAGE_INDEX = 0;

    // The minimum amount of items to have below your current scroll position
    // before loading more.
    private final int visibleThreshold;
    // The current offset index of data you have loaded
    private int currentPage = 0;
    // The total number of items in the data-set after the last load
    private int previousTotalItemCount = 0;
    // True if we are still waiting for the last set of data to load.
    private boolean loading = true;

    private final RecyclerView.LayoutManager layoutManager;
    private final LastVisibleItemPositionFinder lastVisibleItemPositionFinder;

    // Defines the process for actually loading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount, RecyclerView view);

    private interface LastVisibleItemPositionFinder {
        int find();
    }

    @SuppressWarnings("unused")
    protected EndlessRecyclerViewScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.visibleThreshold = VISIBLE_THRESHOLD;
        this.lastVisibleItemPositionFinder = layoutManager::findLastVisibleItemPosition;
    }

    @SuppressWarnings("unused")
    protected EndlessRecyclerViewScrollListener(GridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.visibleThreshold = VISIBLE_THRESHOLD * layoutManager.getSpanCount();
        this.lastVisibleItemPositionFinder = layoutManager::findLastVisibleItemPosition;
    }

    @SuppressWarnings("unused")
    protected EndlessRecyclerViewScrollListener(StaggeredGridLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        this.visibleThreshold = VISIBLE_THRESHOLD * layoutManager.getSpanCount();
        this.lastVisibleItemPositionFinder = () -> {
            final int[] lastVisibleItemPositions = layoutManager.findLastVisibleItemPositions(null);
            // get maximum element within the list
            return getLastVisibleItem(lastVisibleItemPositions);
        };
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        final int lastVisibleItemPosition = lastVisibleItemPositionFinder.find();
        final int totalItemCount = layoutManager.getItemCount();

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < previousTotalItemCount) {
            this.currentPage = STARTING_PAGE_INDEX;
            this.previousTotalItemCount = totalItemCount;

            if (totalItemCount == 0) {
                this.loading = true;
            }
        }

        // If it’s still loading, we check to see if the data-set count has
        // changed, if so we conclude it has finished loading and update the current page
        // number and total item count.
        if (loading && (totalItemCount > previousTotalItemCount)) {
            loading = false;
            previousTotalItemCount = totalItemCount;
        }

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        // threshold should reflect how many total columns there are too
        if (!loading && (lastVisibleItemPosition + visibleThreshold) > totalItemCount) {
            currentPage++;
            onLoadMore(currentPage, totalItemCount, view);
            loading = true;
        }
    }

    private int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;

        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0 || lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }

        return maxSize;
    }

    // Call this method whenever performing new searches
    public void resetState() {
        this.currentPage = STARTING_PAGE_INDEX;
        this.previousTotalItemCount = 0;
        this.loading = true;
    }

}
