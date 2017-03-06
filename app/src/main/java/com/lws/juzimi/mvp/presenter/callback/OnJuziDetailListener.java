package com.lws.juzimi.mvp.presenter.callback;

import com.lws.juzimi.mvp.model.bean.SceneListDetail;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */
public interface OnJuziDetailListener {

    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);

}
