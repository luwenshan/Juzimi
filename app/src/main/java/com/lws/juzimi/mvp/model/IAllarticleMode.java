package com.lws.juzimi.mvp.model;

import android.content.Context;

import com.lws.juzimi.mvp.presenter.callback.OnAllarticleListener;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */

public interface IAllarticleMode {
    void loadArticle(Context context, String type, String page, OnAllarticleListener listener);
}
