package com.lws.juzimi.mvp.presenter;

import android.content.Context;

/**
 * Created by Wenshan.Lu on 2016/12/20.
 */

public interface IMeituMeijuPresenter extends BasePresenter {

    void loadImageText(Context context, boolean isFirst, String type, String page);

    void loadImageText(Context context, boolean isFirst, String page);
}
