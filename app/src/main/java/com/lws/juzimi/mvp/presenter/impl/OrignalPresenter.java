package com.lws.juzimi.mvp.presenter.impl;

import android.content.Context;

import com.lws.juzimi.mvp.model.IOrignalModel;
import com.lws.juzimi.mvp.model.bean.SentenceDetail;
import com.lws.juzimi.mvp.model.impl.OrignalModelImpl;
import com.lws.juzimi.mvp.presenter.IOrignalPresenter;
import com.lws.juzimi.mvp.presenter.callback.OnOrinalListener;
import com.lws.juzimi.mvp.ui.view.IOrignalView;

import java.util.List;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */
public class OrignalPresenter implements IOrignalPresenter, OnOrinalListener {

    private IOrignalView iOrignalView;

    private IOrignalModel iOrignalModel;

    public OrignalPresenter(IOrignalView iOrignalView) {
        this.iOrignalView = iOrignalView;
        this.iOrignalModel = new OrignalModelImpl();
    }

    @Override
    public void loadOriginal(Context context, String type, String page) {
        iOrignalModel.loadOriginal(context, type, page, this);
    }

    @Override
    public void onSuccess(List<SentenceDetail> sentenceDetails) {
        iOrignalView.onSuccess(sentenceDetails);
    }

    @Override
    public void onError(Throwable e) {
        iOrignalView.onError(e);
    }
}
