package com.lws.recyclerviewlibrary.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lws.recyclerviewlibrary.R;

/**
 * Created by Wenshan.Lu on 2016/12/2.
 * <p>
 * ListView/GridView/RecyclerView 分页加载时使用到的FooterView
 */

public class RecyclerViewLoadingFooter extends RelativeLayout {

    protected State mState = State.Normal;
    private View mLoadingView;
    private View mNetworkErrorView;
    private View mTheEndView;
    private ProgressBar mLoadingProgress;
    private TextView mLoadingText;

    public RecyclerViewLoadingFooter(Context context) {
        super(context);
        init(context);
    }

    public RecyclerViewLoadingFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public RecyclerViewLoadingFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.common_list_footer, this);
        setOnClickListener(null);
        setState(State.Normal);
    }

    public State getState() {
        return mState;
    }

    public void setState(State status) {
        setState(status, true);
    }

    /**
     * 设置状态
     *
     * @param status
     * @param showView 是否展示当前View
     */
    public void setState(State status, boolean showView) {
        if (mState == status) {
            return;
        }
        mState = status;

        switch (status) {
            case Normal:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }
                break;
            case Loading:
                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }
                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }
                if (mLoadingView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.loading_viewstub);
                    mLoadingView = viewStub.inflate();

                    mLoadingProgress = (ProgressBar) mLoadingView.findViewById(R.id.loading_progress);
                    mLoadingText = (TextView) mLoadingView.findViewById(R.id.loading_text);
                } else {
                    mLoadingView.setVisibility(VISIBLE);
                }

                mLoadingView.setVisibility(showView ? VISIBLE : GONE);
                mLoadingProgress.setVisibility(VISIBLE);
                mLoadingText.setText(R.string.list_footer_loading);
                break;
            case TheEnd:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }
                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mTheEndView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.end_viewstub);
                    mTheEndView = viewStub.inflate();
                } else {
                    mTheEndView.setVisibility(VISIBLE);
                }
                mTheEndView.setVisibility(showView ? VISIBLE : GONE);
                break;
            case NetworkError:
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }
                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }
                if (mNetworkErrorView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.network_error_viewstub);
                    mNetworkErrorView = viewStub.inflate();
                } else {
                    mNetworkErrorView.setVisibility(VISIBLE);
                }
                mNetworkErrorView.setVisibility(showView ? VISIBLE : GONE);
                break;
            default:
                break;
        }
    }

    public enum State {
        /**
         * 正常
         */
        Normal,
        /**
         * 加载到最底了
         */
        TheEnd,
        /**
         * 加载中...
         */
        Loading,
        /**
         * 网络异常
         */
        NetworkError
    }
}
