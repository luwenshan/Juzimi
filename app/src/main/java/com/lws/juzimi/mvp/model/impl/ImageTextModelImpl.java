package com.lws.juzimi.mvp.model.impl;

import android.content.Context;
import android.text.TextUtils;

import com.apkfuns.logutils.LogUtils;
import com.lws.juzimi.http.Api;
import com.lws.juzimi.http.ServiceFactory;
import com.lws.juzimi.http.service.SentenceService;
import com.lws.juzimi.mvp.model.IImageTextModel;
import com.lws.juzimi.mvp.model.bean.SceneListDetail;
import com.lws.juzimi.mvp.presenter.callback.OnImageTextListener;
import com.lws.juzimi.util.DocParseUtil;
import com.lws.juzimi.util.StringUtil;

import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Wenshan.Lu on 2016/12/21.
 */

public class ImageTextModelImpl implements IImageTextModel {

    private SentenceService mSentenceService;

    private OnImageTextListener mListener;

    private Context mContext;


    @Override
    public void loadMeiju(Context context, boolean isFirst, String type, String page, OnImageTextListener listener) {
        this.mContext = context;
        this.mListener = listener;
        this.mSentenceService = ServiceFactory.getInstance().createService(SentenceService.class, Api.BASE_URL_MEITUMEIJU);

        loadMeiju(isFirst, type, page);
    }

    @Override
    public void loadMeiju(Context context, boolean isFirst, String page, OnImageTextListener listener) {
        this.mContext = context;
        this.mListener = listener;
        this.mSentenceService = ServiceFactory.getInstance().createService(SentenceService.class, Api.BASE_URL_MEITUMEIJU);

        loadMeiju(isFirst, null, page);
    }

    private void loadMeiju(final boolean isFirst, String type, String page) {
        Call<ResponseBody> call = null;

        if (TextUtils.isEmpty(type)) {
            String url = Api.BASE_URL_MEITUMEIJU;

            if (!TextUtils.isEmpty(page)) {
                url = url + "?page=" + page;
            }
            call = mSentenceService.loadMeiju(url);
        } else {
            call = mSentenceService.loadMeiju(type, page);
        }

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                InputStream inputStream = response.body().byteStream();

                String result = StringUtil.inputStreamToString(inputStream);

                SceneListDetail sceneListDetail = DocParseUtil.parseMeiju(isFirst, result);

                mListener.onSuccess(sceneListDetail);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                LogUtils.e(t);
                mListener.onError(t);
            }
        });
    }
}
