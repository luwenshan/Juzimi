package com.lws.juzimi.mvp.presenter;

import android.content.Context;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */

public interface IAllarticlePresenter extends BasePresenter {
    void loadAllarticle(Context context, String type, String page);
}
