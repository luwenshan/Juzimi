package com.lws.juzimi.mvp.ui.view;

import com.lws.juzimi.mvp.model.bean.SentenceCollection;

import java.util.List;

/**
 * 句集
 */
public interface IAlbumsView {
    void onSuccess(List<SentenceCollection> sentenceCollections);

    void onError(Throwable e);
}
