package com.lws.juzimi.mvp.presenter.impl;

import android.content.Context;

import com.lws.juzimi.mvp.model.IAlbumsModel;
import com.lws.juzimi.mvp.model.bean.SentenceCollection;
import com.lws.juzimi.mvp.model.impl.AlbumsModelImpl;
import com.lws.juzimi.mvp.presenter.IAlbumsPresenter;
import com.lws.juzimi.mvp.presenter.callback.OnAlbumsListener;
import com.lws.juzimi.mvp.ui.view.IAlbumsView;

import java.util.List;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */
public class AlbumsPresenter implements IAlbumsPresenter, OnAlbumsListener {

    private IAlbumsView iAlbumsView;

    private IAlbumsModel iAlbumsModel;

    public AlbumsPresenter(IAlbumsView iAlbumsView) {
        this.iAlbumsView = iAlbumsView;
        this.iAlbumsModel = new AlbumsModelImpl();
    }

    @Override
    public void onSuccess(List<SentenceCollection> sentenceCollections) {
        iAlbumsView.onSuccess(sentenceCollections);
    }

    @Override
    public void onError(Throwable e) {
        iAlbumsView.onError(e);
    }

    @Override
    public void loadAlbums(Context context, String type, String page) {
        iAlbumsModel.loadAlbums(context, type, page, this);
    }
}
