package com.lws.recyclerviewlibrary.recyclerview;

import android.view.View;

/**
 * Created by Wenshan.Lu on 2016/12/2.
 * RecyclerView/ListView/GridView 滑动加载下一页时的回调接口
 */

public interface OnListLoadNextPageListener {
    /**
     * 开始加载下一页
     *
     * @param view 当前RecyclerView/ListView/GridView
     */
    void onLoadNextPage(View view);
}
