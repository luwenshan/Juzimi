package com.lws.juzimi.mvp.model;

import android.content.Context;

import com.lws.juzimi.mvp.presenter.callback.OnJuziDetailListener;
/**
 * Created by Wenshan.Lu on 2016/12/28.
 */
public interface IJuziDetailModel {

    void loadOriginal(Context context, boolean isFrist, String url, OnJuziDetailListener listener);
}
