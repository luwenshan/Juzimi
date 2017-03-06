package com.lws.juzimi.mvp.presenter.impl;

import android.content.Context;

import com.lws.juzimi.mvp.model.IJuziDetailModel;
import com.lws.juzimi.mvp.model.bean.SceneListDetail;
import com.lws.juzimi.mvp.model.impl.JuziDetailModelImpl;
import com.lws.juzimi.mvp.presenter.IJuziDetailPresenter;
import com.lws.juzimi.mvp.presenter.callback.OnJuziDetailListener;
import com.lws.juzimi.mvp.ui.view.IJuziDetailView;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */
public class JuziDetailPresenter implements IJuziDetailPresenter, OnJuziDetailListener {

    private IJuziDetailView mIJuziDetailView;

    private IJuziDetailModel mIJuziDetailModel;

    public JuziDetailPresenter(IJuziDetailView mIJuziDetailView) {
        this.mIJuziDetailView = mIJuziDetailView;
        this.mIJuziDetailModel = new JuziDetailModelImpl();
    }

    @Override
    public void onSuccess(SceneListDetail sceneListDetail) {
        mIJuziDetailView.onSuccess(sceneListDetail);
    }

    @Override
    public void onError(Throwable e) {
        mIJuziDetailView.onError(e);
    }

    @Override
    public void loadJuziDetail(Context context, boolean isFrist, String url) {
        mIJuziDetailModel.loadOriginal(context, isFrist, url, this);
    }
}
