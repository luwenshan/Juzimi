package com.lws.juzimi.mvp.ui.view;

import com.lws.juzimi.mvp.model.bean.SceneListDetail;

/**
 * 句子详情
 */
public interface IJuziDetailView {

    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);
}
