package com.lws.recyclerviewlibrary.recyclerview;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Wenshan.Lu on 2016/12/2.
 * <p>
 * RecyclerView为GridLayoutManager时，设置了HeaderView，就会用到这个SpanSizeLookup
 */

public class HeaderSpanSizeLookup extends GridLayoutManager.SpanSizeLookup {

    private HeaderAndFooterRecyclerViewAdapter mAdapter;
    private RecyclerView.Adapter adapter;

    private int mSpanSize = 1;

    public HeaderSpanSizeLookup(RecyclerView.Adapter adapter, int spanSize) {
        this.adapter = adapter;
        this.mSpanSize = spanSize;
    }

    @Override
    public int getSpanSize(int position) {
        boolean isHeaderOrFooter = false;
        if (adapter instanceof HeaderAndFooterRecyclerViewAdapter) {
            mAdapter = (HeaderAndFooterRecyclerViewAdapter) adapter;
            isHeaderOrFooter = mAdapter.isHeader(position) || mAdapter.isFooter(position);
        }
        return isHeaderOrFooter ? mSpanSize : 1;
    }
}
