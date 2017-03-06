package com.lws.juzimi.mvp.presenter.impl;

import android.content.Context;

import com.lws.juzimi.mvp.model.IImageTextModel;
import com.lws.juzimi.mvp.model.bean.SceneListDetail;
import com.lws.juzimi.mvp.model.impl.ImageTextModelImpl;
import com.lws.juzimi.mvp.presenter.IMeituMeijuPresenter;
import com.lws.juzimi.mvp.presenter.callback.OnImageTextListener;
import com.lws.juzimi.mvp.ui.view.IMeituMeijuView;

/**
 * Created by Wenshan.Lu on 2016/12/21.
 */

public class ImgTextPresenter implements IMeituMeijuPresenter, OnImageTextListener {

    private IMeituMeijuView iMeituMeijuView;
    private IImageTextModel iImageTextModel;

    public ImgTextPresenter(IMeituMeijuView iMeituMeijuView) {
        this.iMeituMeijuView = iMeituMeijuView;
        iImageTextModel = new ImageTextModelImpl();
    }

    @Override
    public void loadImageText(Context context, boolean isFirst, String type, String page) {
        iImageTextModel.loadMeiju(context, isFirst, type, page, this);
    }

    @Override
    public void loadImageText(Context context, boolean isFirst, String page) {
        iImageTextModel.loadMeiju(context, isFirst, page, this);
    }

    @Override
    public void onSuccess(SceneListDetail sceneListDetail) {
        iMeituMeijuView.onSuccess(sceneListDetail);
    }

    @Override
    public void onError(Throwable e) {
        iMeituMeijuView.onError(e);
    }
}
