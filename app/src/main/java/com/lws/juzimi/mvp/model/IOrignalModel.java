package com.lws.juzimi.mvp.model;

import android.content.Context;

import com.lws.juzimi.mvp.presenter.callback.OnOrinalListener;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 *
 * 原创句子
 */
public interface IOrignalModel {

    void loadOriginal(Context context, String type, String page, OnOrinalListener listener);

}
