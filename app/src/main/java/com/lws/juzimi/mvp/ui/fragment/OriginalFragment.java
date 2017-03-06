package com.lws.juzimi.mvp.ui.fragment;


import android.os.Bundle;
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
 * 设置frag
 */
public class OriginalFragment extends BaseFragment {


    private static final String TYPE1 = "ju";

    private static final String TYPE2 = "week";

    private static final String TYPE3 = "recommend";

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private View view;

    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (view == null) {
            view = inflater.inflate(R.layout.fragment_original, container, false);
        }

        unbinder = ButterKnife.bind(this, view);

        initControls();

        return view;

    }

    private void initControls() {

        //初始化各fragment
        OriginalListFragment originalListFragment1 = OriginalListFragment.newInstance(TYPE1);
        OriginalListFragment originalListFragment2 = OriginalListFragment.newInstance(TYPE2);
        OriginalListFragment fragmentOriginalLis3 = OriginalListFragment.newInstance(TYPE3);

        //将fragment装进列表中
        List<Fragment> list_fragment = new ArrayList<>();
        list_fragment.add(originalListFragment1);
        list_fragment.add(originalListFragment2);
        list_fragment.add(fragmentOriginalLis3);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        List<String> list_title = new ArrayList<>();
        list_title.add("最新原创");
        list_title.add("本周热门");
        list_title.add("推荐原创");

        //设置TabLayout的模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //为TabLayout添加tab名称
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(0)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(1)));
        tabLayout.addTab(tabLayout.newTab().setText(list_title.get(2)));

        TitleTabAdapter titleTabAdapter = new TitleTabAdapter(getChildFragmentManager(), list_fragment, list_title);

        //viewpager加载adapter
        viewPager.setAdapter(titleTabAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onDestroyView() {

        super.onDestroyView();

        unbinder.unbind();
    }

}
