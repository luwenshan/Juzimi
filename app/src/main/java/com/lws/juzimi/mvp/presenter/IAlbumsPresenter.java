package com.lws.juzimi.mvp.presenter;

import android.content.Context;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 * <p>
 * 句集
 */
public interface IAlbumsPresenter {

    void loadAlbums(Context context, String type, String page);
}
