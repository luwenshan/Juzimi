package com.lws.juzimi.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.lws.juzimi.R;
import com.lws.juzimi.mvp.model.bean.UpgradeBean;
import com.lws.juzimi.util.AppUtil;

import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;
/**
 * Created by Wenshan.Lu on 2016/12/20.
 */
public class SplashActivity extends BaseActivity {

    UpgradeBean mUpgradeBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!isTaskRoot()) {
            //判断该Activity是不是任务空间的源Activity，“非”也就是说是被系统重新实例化出来
            //如果你就放在launcher Activity中话，这里可以直接return了
            Intent mainIntent = getIntent();
            String action = mainIntent.getAction();
            if (mainIntent.hasCategory(Intent.CATEGORY_LAUNCHER) && action.equals(Intent.ACTION_MAIN)) {
                finish();
                return;//finish()之后该活动会继续执行后面的代码，你可以logCat验证，加return避免可能的exception
            }
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        checkUpdate();
    }

    private void checkUpdate() {
        FIR.checkForUpdateInFIR("088041a4237beb050b98d00c13b83caf", new VersionCheckCallback() {
            @Override
            public void onFail(Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onFinish() {
                Log.i("fir", "onFinish");
                try {
                    //判断是否需要更新
                    if (mUpgradeBean.getVersion() > AppUtil.getVersionCode(SplashActivity.this)) {
                        String update_desc = mUpgradeBean.getChangelog();
                        showDialog(update_desc);
                    } else {
                        jumpToMain();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    jumpToMain();
                }
            }

            @Override
            public void onStart() {
                Log.i("fir", "onStart");
            }

            @Override
            public void onSuccess(String s) {
                Log.i("fir", "onSuccess " + "\n" + s);
                Gson gson = new Gson();
                mUpgradeBean = gson.fromJson(s, UpgradeBean.class);
            }
        });
    }

    /**
     * 兼容的Dialog
     *
     * @param update_desc
     */
    private void showDialog(String update_desc) {
        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);
        builder.setTitle("升级提示")
                .setMessage(update_desc)
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        jumpToMain();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse(mUpgradeBean.getInstall_url());
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        SplashActivity.this.startActivity(intent);
                    }
                });
        builder.create().show();
    }

    /**
     * 跳转到 MainActivity
     */
    private void jumpToMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
}
