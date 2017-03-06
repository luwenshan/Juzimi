package com.lws.recyclerviewlibrary.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Wenshan.Lu on 2016/12/14.
 * <p>
 * RecyclerView设置Header/Footer所用到的工具类
 */

public class RecyclerViewUtils {

    /**
     * 设置HeaderView
     *
     * @param recyclerView
     * @param view
     */
    public static void setHeaderView(RecyclerView recyclerView, View view) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
        if (headerAndFooterAdapter.getHeaderViewsCount() == 0) {
            headerAndFooterAdapter.addHeaderView(view);
        }
    }

    public static void setFooterView(RecyclerView recyclerView, View view) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter == null || !(outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            return;
        }

        HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
        if (headerAndFooterAdapter.getFooterViewsCount() == 0) {
            headerAndFooterAdapter.addFooterView(view);
        }
    }

    /**
     * 移除第一个FooterView
     *
     * @param recyclerView
     */
    public static void removeFooterView(RecyclerView recyclerView) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
            if (headerAndFooterAdapter.getFooterViewsCount() > 0) {
                View footerView = headerAndFooterAdapter.getFooterView();
                headerAndFooterAdapter.removeFooterView(footerView);
            }
        }
    }

    /**
     * 移除第一个HeaderView
     *
     * @param recyclerView
     */
    public static void removeHeaderView(RecyclerView recyclerView) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
            if (headerAndFooterAdapter.getHeaderViewsCount() > 0) {
                View headerView = headerAndFooterAdapter.getHeaderView();
                headerAndFooterAdapter.removeHeaderView(headerView);
            }
        }
    }

    /**
     * 使用本方法替代RecyclerView.ViewHolder的getLayoutPosition()方法
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    public static int getLayoutPosition(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
            int headerViewsCount = headerAndFooterAdapter.getHeaderViewsCount();
            if (headerViewsCount > 0) {
                return viewHolder.getLayoutPosition() - headerViewsCount;
            }
        }
        return viewHolder.getLayoutPosition();
    }

    /**
     * 使用本方法替代RecyclerView.ViewHolder的getAdapterPosition()方法
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    public static int getAdapterPosition(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        RecyclerView.Adapter outerAdapter = recyclerView.getAdapter();
        if (outerAdapter != null && (outerAdapter instanceof HeaderAndFooterRecyclerViewAdapter)) {
            HeaderAndFooterRecyclerViewAdapter headerAndFooterAdapter = (HeaderAndFooterRecyclerViewAdapter) outerAdapter;
            int headerViewsCount = headerAndFooterAdapter.getHeaderViewsCount();
            if (headerViewsCount > 0) {
                return viewHolder.getAdapterPosition() - headerViewsCount;
            }
        }
        return viewHolder.getAdapterPosition();
    }
}
