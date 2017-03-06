package com.lws.juzimi.app;

import android.app.Application;

import com.apkfuns.logutils.LogUtils;
import com.lws.juzimi.common.CrashHandler;

import im.fir.sdk.FIR;

/**
 * Created by Wenshan.Lu on 2016/12/19.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FIR.init(this);

        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());

        LogUtils.configAllowLog = true;
        LogUtils.configTagPrefix = "lws-";
    }
}
