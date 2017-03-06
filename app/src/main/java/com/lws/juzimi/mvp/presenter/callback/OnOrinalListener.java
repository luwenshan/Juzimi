package com.lws.juzimi.mvp.presenter.callback;


import com.lws.juzimi.mvp.model.bean.SentenceDetail;

import java.util.List;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */
public interface OnOrinalListener {

    void onSuccess(List<SentenceDetail> sentenceDetails);

    void onError(Throwable e);

}
