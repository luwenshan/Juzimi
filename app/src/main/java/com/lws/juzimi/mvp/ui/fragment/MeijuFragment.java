package com.lws.juzimi.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lws.juzimi.R;
import com.lws.juzimi.mvp.ui.adapter.TitleTabAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Wenshan.Lu on 2016/12/22.
 */

public class MeijuFragment extends BaseFragment {
    private static final String TYPE1 = null;
    private static final String TYPE2 = "shouxiemeiju";
    private static final String TYPE3 = "jingdianduibai";

    @BindView(R.id.tab_layout_meiju)
    TabLayout mTabLayout;
    @BindView(R.id.view_pager_meiju)
    ViewPager mViewPager;

    private Unbinder mUnbinder;

    private View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(R.layout.fragment_meiju, container, false);
        }
        mUnbinder = ButterKnife.bind(this, mRootView);
        initControls();
        return mRootView;
    }

    private void initControls() {
        Fragment fragment1 = MeituMeijuFragment.newInstance(TYPE1);
        Fragment fragment2 = MeituMeijuFragment.newInstance(TYPE2);
        Fragment fragment3 = MeituMeijuFragment.newInstance(TYPE3);

        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(fragment1);
        fragmentList.add(fragment2);
        fragmentList.add(fragment3);

        List<String> titleList = new ArrayList<>();
        titleList.add("美图美句");
        titleList.add("手写句子");
        titleList.add("经典对白");

        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        mTabLayout.addTab(mTabLayout.newTab().setText(titleList.get(0)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titleList.get(1)));
        mTabLayout.addTab(mTabLayout.newTab().setText(titleList.get(2)));

        TitleTabAdapter titleTabAdapter = new TitleTabAdapter(getChildFragmentManager(), fragmentList, titleList);

        mViewPager.setAdapter(titleTabAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
