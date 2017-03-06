package com.lws.juzimi.http.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * Created by Wenshan.Lu on 2016/12/19.
 */

public interface SentenceService {

    @GET("{type}")
    Call<ResponseBody> loadAllarticle(@Path("type") String type, @Query("page") String page);

    @GET("{type}")
    Call<ResponseBody> loadOriginal(@Path("type") String type, @Query("page") String page);

    @GET("{type}")
    Call<ResponseBody> loadAlbums(@Path("type") String type, @Query("page") String page);

    //美图美句
    @GET
    Call<ResponseBody> loadMeiju(@Url String url);

    //手写美句
    @GET("{type}")
    Call<ResponseBody> loadMeiju(@Path("type") String type, @Query("page") String page);

    //句子详情
    @GET
    Call<ResponseBody> loadJuziDetail(@Url String url);
}
