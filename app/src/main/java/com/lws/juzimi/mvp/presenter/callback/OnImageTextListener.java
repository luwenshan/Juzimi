package com.lws.juzimi.mvp.presenter.callback;

import com.lws.juzimi.mvp.model.bean.SceneListDetail;

/**
 * Created by Wenshan.Lu on 2016/12/21.
 *
 * 获取数据之后的回调接口，在Presenter中回调处理
 */

public interface OnImageTextListener {

    void onSuccess(SceneListDetail sceneListDetail);

    void onError(Throwable e);
}
