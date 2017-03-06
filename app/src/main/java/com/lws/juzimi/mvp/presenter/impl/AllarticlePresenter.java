package com.lws.juzimi.mvp.presenter.impl;

import android.content.Context;

import com.lws.juzimi.mvp.model.IAllarticleMode;
import com.lws.juzimi.mvp.model.bean.SentenceSimple;
import com.lws.juzimi.mvp.model.impl.AllArticleModelImpl;
import com.lws.juzimi.mvp.presenter.IAllarticlePresenter;
import com.lws.juzimi.mvp.presenter.callback.OnAllarticleListener;
import com.lws.juzimi.mvp.ui.view.IAllarticleView;

import java.util.List;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */

public class AllarticlePresenter implements IAllarticlePresenter, OnAllarticleListener {

    private IAllarticleView iAllarticleView;

    private IAllarticleMode iAllarticleModel;

    public AllarticlePresenter(IAllarticleView iAllarticleView) {
        this.iAllarticleView = iAllarticleView;
        this.iAllarticleModel = new AllArticleModelImpl();
    }

    @Override
    public void loadAllarticle(Context context, String type, String page) {
        iAllarticleModel.loadArticle(context, type, page, this);
    }

    @Override
    public void onSuccess(List<SentenceSimple> sentenceSimples) {
        iAllarticleView.onSuccess(sentenceSimples);
    }

    @Override
    public void onError(Throwable e) {
        iAllarticleView.onError(e);
    }
}
