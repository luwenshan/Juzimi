package com.lws.juzimi.mvp.ui.view;

import com.lws.juzimi.mvp.model.bean.SceneListDetail;

/**
 * Created by Wenshan.Lu on 2016/12/20.
 *
 * 美图美句
 */

public interface IMeituMeijuView extends BaseView {
    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);
}
