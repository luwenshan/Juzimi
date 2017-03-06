package com.lws.juzimi.mvp.model.impl;

import android.content.Context;

import com.lws.juzimi.http.Api;
import com.lws.juzimi.http.ServiceFactory;
import com.lws.juzimi.http.service.SentenceService;
import com.lws.juzimi.mvp.model.IOrignalModel;
import com.lws.juzimi.mvp.model.bean.SentenceDetail;
import com.lws.juzimi.mvp.presenter.callback.OnOrinalListener;
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
public class OrignalModelImpl implements IOrignalModel {

    private SentenceService mSentenceService;

    private OnOrinalListener mListener;

    private Context mContext;

    @Override
    public void loadOriginal(Context context, String type, String page, OnOrinalListener listener) {

        this.mContext = context;
        this.mListener = listener;

        this.mSentenceService = ServiceFactory.getInstance().createService(SentenceService.class, Api.BASE_URL_ORIGINAL);

        loadOriginal(type, page);
    }

    private void loadOriginal(String type, String page) {

        Call<ResponseBody> call = mSentenceService.loadOriginal(type, page);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                InputStream inputStream = response.body().byteStream();

                String result = StringUtil.inputStreamToString(inputStream);

                List<SentenceDetail> sentenceDetails = null;
                try {
                    sentenceDetails = DocParseUtil.parseOrignal(result);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                mListener.onSuccess(sentenceDetails);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mListener.onError(t);
            }
        });

    }
}
