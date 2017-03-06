package com.lws.recyclerviewlibrary.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

/**
 * Created by Wenshan.Lu on 2016/12/2.
 * <p>
 * 拓展的StaggeredGridLayoutManager
 */

public class ExStaggeredGridLayoutManager extends StaggeredGridLayoutManager {

    private final String TAG = getClass().getSimpleName();

    private GridLayoutManager.SpanSizeLookup mSpanSizeLookup;

    public ExStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    /**
     * Returns the current SpanSizeLookup used by the GridLayoutManager.
     *
     * @return
     */
    public GridLayoutManager.SpanSizeLookup getSpanSizeLookup() {
        return mSpanSizeLookup;
    }

    /**
     * 设置某个位置的item的跨列程度，这里和GridLayoutManager有点不一样，
     * 如果你设置某个位置的item的 span > 1 了，那么这个item会占据所有列
     *
     * @param spanSizeLookup instance to be used to query number of spans
     *                       occupied by each item.
     */
    public void setSpanSizeLookup(GridLayoutManager.SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

    @Override
    public void onMeasure(RecyclerView.Recycler recycler, RecyclerView.State state, int widthSpec, int heightSpec) {
        for (int i = 0; i < getItemCount(); i++) {
            if (mSpanSizeLookup.getSpanSize(i) > 1) {
                try {
                    //fix 动态添加时报IndexOutOfBoundsException
                    View view = recycler.getViewForPosition(i);
                    if (view != null) {
                        /**
                         * 占用所有的列
                         */
                        LayoutParams lp = (LayoutParams) view.getLayoutParams();
                        lp.setFullSpan(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        super.onMeasure(recycler, state, widthSpec, heightSpec);
    }
}
