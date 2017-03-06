package com.lws.juzimi.mvp.model;

import android.content.Context;

import com.lws.juzimi.mvp.presenter.callback.OnImageTextListener;

/**
 * Created by Wenshan.Lu on 2016/12/21.
 *
 * 获取数据的接口
 */

public interface IImageTextModel {

    void loadMeiju(Context context, boolean isFirst, String type, String page, OnImageTextListener listener);

    void loadMeiju(Context context, boolean isFirst, String page, OnImageTextListener listener);
}
