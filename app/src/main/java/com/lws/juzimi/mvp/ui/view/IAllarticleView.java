package com.lws.juzimi.mvp.ui.view;

import com.lws.juzimi.mvp.model.bean.SentenceSimple;

import java.util.List;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */

public interface IAllarticleView extends BaseView {
    void onSuccess(List<SentenceSimple> sentenceSimples);

    void onError(Throwable e);
}
