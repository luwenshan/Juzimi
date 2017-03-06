package com.lws.juzimi.mvp.model.impl;

import android.content.Context;
import android.util.Log;

import com.lws.juzimi.http.Api;
import com.lws.juzimi.http.ServiceFactory;
import com.lws.juzimi.http.service.SentenceService;
import com.lws.juzimi.mvp.model.IAllarticleMode;
import com.lws.juzimi.mvp.model.bean.SentenceSimple;
import com.lws.juzimi.mvp.presenter.callback.OnAllarticleListener;
import com.lws.juzimi.util.DocParseUtil;
import com.lws.juzimi.util.StringUtil;

import java.io.InputStream;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Wenshan.Lu on 2016/12/28.
 */

public class AllArticleModelImpl implements IAllarticleMode {

    private SentenceService mSentenceService;

    private OnAllarticleListener mListener;

    private Context mContext;

    @Override
    public void loadArticle(Context context, String type, String page, OnAllarticleListener listener) {
        mContext = context;
        mListener = listener;

        mSentenceService = ServiceFactory.getInstance().createService(SentenceService.class, Api.BASE_URL_ALLARTICLE);

        loadArticle(type, page);
    }

    private void loadArticle(String type, String page) {
        Call<ResponseBody> call = mSentenceService.loadAllarticle(type, page);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream inputStream = response.body().byteStream();
                String result = StringUtil.inputStreamToString(inputStream);
                Log.d("Debug", result);
                List<SentenceSimple> sentenceSimples = DocParseUtil.parseAllarticle(result);
                mListener.onSuccess(sentenceSimples);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mListener.onError(t);
            }
        });
    }
}
