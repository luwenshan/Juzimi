package com.lws.juzimi.mvp.model;

import android.content.Context;

import com.lws.juzimi.mvp.presenter.callback.OnAlbumsListener;


/**
 * Created by Wenshan.Lu on 2016/12/28.
 * <p>
 * 原创句子
 */
public interface IAlbumsModel {

    void loadAlbums(Context context, String type, String page, OnAlbumsListener listener);

}
