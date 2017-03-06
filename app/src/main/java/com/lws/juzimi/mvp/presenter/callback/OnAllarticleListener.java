package com.lws.juzimi.mvp.presenter.callback;

import com.lws.juzimi.mvp.model.bean.SentenceSimple;

import java.util.List;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */

public interface OnAllarticleListener {
    void onSuccess(List<SentenceSimple> sentenceSimples);

    void onError(Throwable e);
}
