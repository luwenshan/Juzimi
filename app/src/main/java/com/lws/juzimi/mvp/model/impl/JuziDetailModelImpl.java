package com.lws.juzimi.mvp.model.impl;

import android.content.Context;

import com.lws.juzimi.http.Api;
import com.lws.juzimi.http.ServiceFactory;
import com.lws.juzimi.http.service.SentenceService;
import com.lws.juzimi.mvp.model.IJuziDetailModel;
import com.lws.juzimi.mvp.model.bean.SceneListDetail;
import com.lws.juzimi.mvp.presenter.callback.OnJuziDetailListener;
import com.lws.juzimi.util.DocParseUtil;
import com.lws.juzimi.util.StringUtil;

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Wenshan.Lu on 2016/12/28.
 */
public class JuziDetailModelImpl implements IJuziDetailModel {

    private SentenceService mSentenceService;

    private OnJuziDetailListener mListener;

    private Context mContext;

    @Override
    public void loadOriginal(Context context, boolean isFrist, String url, OnJuziDetailListener listener) {
        this.mContext = context;
        this.mListener = listener;

        this.mSentenceService = ServiceFactory.getInstance().createService(SentenceService.class, Api.BASE_URL_ORIGINAL);

        loadData(isFrist, url);
    }

    private void loadData(final boolean isFrist, String url) {

        Call<ResponseBody> call = mSentenceService.loadJuziDetail(url);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                SceneListDetail sceneListDetail = null;

                if (response != null && response.body() != null) {
                    InputStream inputStream = response.body().byteStream();

                    String result = StringUtil.inputStreamToString(inputStream);
//                    System.out.println(result);

                    try {
                        sceneListDetail = DocParseUtil.parseJuziDetail(isFrist, result);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                mListener.onSuccess(sceneListDetail);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                mListener.onError(t);
            }
        });

    }


}
