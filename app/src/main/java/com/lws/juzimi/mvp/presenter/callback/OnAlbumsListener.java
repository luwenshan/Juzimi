package com.lws.juzimi.mvp.presenter.callback;

import com.lws.juzimi.mvp.model.bean.SentenceCollection;

import java.util.List;
/**
 * Created by Wenshan.Lu on 2016/12/28.
 */
public interface OnAlbumsListener {

    void onSuccess(List<SentenceCollection> sentenceCollections);

    void onError(Throwable e);

}
