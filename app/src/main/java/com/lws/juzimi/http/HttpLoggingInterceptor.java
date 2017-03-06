package com.lws.juzimi.http;

import com.apkfuns.logutils.LogUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Wenshan.Lu on 2016/12/19.
 * <p>
 * retrofit 拦截
 * <p>
 * Observes, modifies, and potentially short-circuits requests going out and the corresponding
 * responses coming back in. Typically interceptors add, remove, or transform headers on the request
 * or response.
 */

public class HttpLoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String requestStartMessage = request.method() + ' ' + request.url();

        LogUtils.e(requestStartMessage);

        long startNs = System.nanoTime();
        Response response = chain.proceed(request);
        long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

        LogUtils.e(response.code() + ' ' + response.message() + "(" + tookMs + "ms" + ')');
        return response;
    }
}
