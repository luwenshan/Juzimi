package com.lws.juzimi.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apkfuns.logutils.LogUtils;
import com.lws.juzimi.R;
import com.lws.juzimi.mvp.model.bean.SceneListDetail;
import com.lws.juzimi.mvp.model.bean.SentenceImageText;
import com.lws.juzimi.mvp.presenter.IMeituMeijuPresenter;
import com.lws.juzimi.mvp.presenter.impl.ImgTextPresenter;
import com.lws.juzimi.mvp.ui.adapter.MeituMeijuAdapter;
import com.lws.juzimi.mvp.ui.view.IMeituMeijuView;
import com.lws.recyclerviewlibrary.flexibledivider.HorizontalDividerItemDecoration;
import com.lws.recyclerviewlibrary.recyclerview.EndlessRecyclerOnScrollListener;
import com.lws.recyclerviewlibrary.recyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.lws.recyclerviewlibrary.recyclerview.RecyclerViewLoadingFooter;
import com.lws.recyclerviewlibrary.recyclerview.RecyclerViewStateUtils;
import com.victor.loading.rotate.RotateLoading;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wenshan.Lu on 2016/12/20.
 */

public class MeituMeijuFragment extends BaseFragment implements IMeituMeijuView {

    private static final String ARG_TYPE = "type";


    RecyclerView mRecyclerView;

    SwipeRefreshLayout mSwipeRefreshLayout;

    RotateLoading mRotateLoading;

    private String mType;

    private IMeituMeijuPresenter mPresenter;

    private View mRootView;

    private List<SentenceImageText> mDatas;

    private HeaderAndFooterRecyclerViewAdapter mAdapter;

    private String mPage;

    private boolean mHasMore;

    private boolean mIsRefresh;

    private String mTotalPage;

    public MeituMeijuFragment() {
    }

    public static MeituMeijuFragment newInstance(String type) {
        MeituMeijuFragment fragment = new MeituMeijuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mType = args.getString(ARG_TYPE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_meitu_meiju, container, false);
            mPresenter = new ImgTextPresenter(this);
            initView();
            mRotateLoading.start();
            queryMeituMeiju();
        }
        return mRootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mRootView != null) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    private void initView() {

        mSwipeRefreshLayout = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_layou_meitu_meiju);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycler_view_meitu_meiju);
        mRotateLoading = (RotateLoading) mRootView.findViewById(R.id.rotate_loading_meitu_meiju);

        mDatas = new ArrayList<>();

        MeituMeijuAdapter meituMeijuAdapter = new MeituMeijuAdapter(getContext(), mDatas, mOnClickListener);
        mAdapter = new HeaderAndFooterRecyclerViewAdapter(meituMeijuAdapter);

        if (mRecyclerView == null) {
            Log.d("Debug", "mRecyclerView==null");
        }

        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addItemDecoration(
                new HorizontalDividerItemDecoration.Builder(getActivity())
                        .colorResId(R.color.divider_color)
                        .size(4)
                        .build());
        mRecyclerView.addOnScrollListener(mOnScrollListener);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.refresh_color));
        mSwipeRefreshLayout.setOnRefreshListener(mOnRefreshListener);
    }

    SwipeRefreshLayout.OnRefreshListener mOnRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mPage = null;
            mIsRefresh = true;

            queryMeituMeiju();
        }
    };


    @Override
    public void onSuccess(SceneListDetail sceneListDetail) {
        List<SentenceImageText> sentenceImageTexts = sceneListDetail.mImageTexts;

        if (mPage == null) {
            mPage = "1";
        } else {
            int pageInt = Integer.parseInt(mPage);
            pageInt++;
            mPage = pageInt + "";
        }

        if (mIsRefresh) {
            mDatas.clear();
            mIsRefresh = false;
            mTotalPage = sceneListDetail.page;
        }
        if (mPage.equals(mTotalPage)) {
            mHasMore = false;
        }

        LogUtils.e("mHasMore : " + mHasMore + "  currentpage : " + mPage + "  totalpage : " + mTotalPage);

        if (sentenceImageTexts != null) {
            mDatas.addAll(sentenceImageTexts);
            mAdapter.notifyDataSetChanged();
        }
        mRotateLoading.stop();
        mSwipeRefreshLayout.setRefreshing(false);

        RecyclerViewStateUtils.setFooterViewState(mRecyclerView, RecyclerViewLoadingFooter.State.Normal);
    }

    @Override
    public void onError(Throwable e) {
        mSwipeRefreshLayout.setRefreshing(false);
        mRotateLoading.stop();
        RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, mHasMore, RecyclerViewLoadingFooter.State.NetworkError, mFooterClickListener);
    }

    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int pos = (int) v.getTag();
            SentenceImageText itemBean = mDatas.get(pos);
            LogUtils.e(itemBean);
        }
    };

    private EndlessRecyclerOnScrollListener mOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            RecyclerViewLoadingFooter.State state = RecyclerViewStateUtils.getFooterViewState(mRecyclerView);
            if (state == RecyclerViewLoadingFooter.State.Loading) {
                return;
            }
            if (mHasMore) {
                RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, mHasMore, RecyclerViewLoadingFooter.State.Loading, null);
                queryMeituMeiju();
            } else {
                RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, mHasMore, RecyclerViewLoadingFooter.State.TheEnd, null);
            }
        }
    };

    private View.OnClickListener mFooterClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, mHasMore, RecyclerViewLoadingFooter.State.Loading, null);
            queryMeituMeiju();
        }
    };

    private void queryMeituMeiju() {
        if (TextUtils.isEmpty(mType)) {
            mPresenter.loadImageText(getActivity(), mIsRefresh, mPage);
        } else {
            mPresenter.loadImageText(getActivity(), mIsRefresh, mType, mPage);
        }
    }
}
