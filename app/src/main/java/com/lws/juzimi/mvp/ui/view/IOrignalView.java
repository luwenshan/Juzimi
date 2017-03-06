package com.lws.juzimi.mvp.ui.view;

import com.lws.juzimi.mvp.model.bean.SentenceDetail;

import java.util.List;

/**
 * 原创句子
 */
public interface IOrignalView {

    void onSuccess(List<SentenceDetail> sentenceDetails);

    void onError(Throwable e);
}
