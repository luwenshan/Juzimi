package com.lws.juzimi.mvp.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.lws.juzimi.R;
import com.lws.juzimi.common.ActivityController;
import com.lws.juzimi.mvp.ui.fragment.AllArticleFragment;
import com.lws.juzimi.mvp.ui.fragment.JujiFragment;
import com.lws.juzimi.mvp.ui.fragment.OriginalFragment;
import com.lws.juzimi.mvp.ui.fragment.MeijuFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Wenshan.Lu on 2016/12/20.
 */
public class MainActivity extends BaseActivity implements BottomNavigationBar.OnTabSelectedListener {

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private Fragment mMeijuFragment;
    private Fragment mAllArticleFragment;
    private Fragment mJujiFragment;
    private Fragment mOriginalFragment;

    private Unbinder mUnbinder;

    // 定义一个变量，来标识是否退出
    private static boolean enableExit = false;

    // 处理请求返回信息
    private MyHandler mHandler = new MyHandler();

    private static class MyHandler extends Handler {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    enableExit = false;
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUnbinder = ButterKnife.bind(this);

        //隐藏掉整个ActionBar，包括下面的Tabs
        getSupportActionBar().hide();

        initBottomNavBar();
    }

    //初始化底部导航栏
    private void initBottomNavBar() {
        mBottomNavigationBar.setAutoHideEnabled(false);//自动隐藏
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar.setBarBackgroundColor(R.color.white);//背景颜色
        mBottomNavigationBar.setInActiveColor(R.color.bottom_nav_normal);//未选中时的颜色
        mBottomNavigationBar.setActiveColor(R.color.bottom_nav_selected);//选中时的颜色

        BottomNavigationItem meijuItem = new BottomNavigationItem(R.mipmap.icon_linggan, "灵感");
        BottomNavigationItem allArticleItem = new BottomNavigationItem(R.mipmap.icon_jingdian, "经典");
        BottomNavigationItem jujiItem = new BottomNavigationItem(R.mipmap.icon_juji, "句集");
        BottomNavigationItem originalItem = new BottomNavigationItem(R.mipmap.icon_yuanchuang, "原创");

        mBottomNavigationBar.
                addItem(meijuItem).
                addItem(allArticleItem).
                addItem(jujiItem).
                addItem(originalItem);
        mBottomNavigationBar.setFirstSelectedPosition(0);
        mBottomNavigationBar.initialise();
        mBottomNavigationBar.setTabSelectedListener(this);

        setDefaultFragment();
    }

    private void setDefaultFragment() {
        if (mMeijuFragment == null) {
            mMeijuFragment = new MeijuFragment();
        }
        addFragment(mMeijuFragment);
        getSupportFragmentManager().beginTransaction().show(mMeijuFragment).commit();
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragment != null && !fragment.isAdded()) {
            fragmentTransaction.add(R.id.bottom_navigation_content, fragment);
        }
        fragmentTransaction.commit();
    }

    private void hideAllFragments() {
        hideFragment(mMeijuFragment);
        hideFragment(mJujiFragment);
        hideFragment(mAllArticleFragment);
        hideFragment(mOriginalFragment);
    }

    /**
     * 隐藏单个Fragment
     *
     * @param fragment
     */
    private void hideFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (fragment != null && fragment.isAdded()) {
            fragmentTransaction.hide(fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onTabSelected(int position) {
        hideAllFragments();
        switch (position) {
            case 0:
                if (mMeijuFragment == null) {
                    mMeijuFragment = new MeijuFragment();
                }
                addFragment(mMeijuFragment);
                getSupportFragmentManager().beginTransaction().show(mMeijuFragment).commit();
                break;
            case 1:
                if (mAllArticleFragment == null) {
                    mAllArticleFragment = new AllArticleFragment();
                }
                addFragment(mAllArticleFragment);
                getSupportFragmentManager().beginTransaction().show(mAllArticleFragment).commit();
                break;
            case 2:
                if (mJujiFragment == null) {
                    mJujiFragment = new JujiFragment();
                }
                addFragment(mJujiFragment);
                getSupportFragmentManager().beginTransaction().show(mJujiFragment).commit();
                break;
            case 3:
                if (mOriginalFragment == null) {
                    mOriginalFragment = new OriginalFragment();
                }
                addFragment(mOriginalFragment);
                getSupportFragmentManager().beginTransaction().show(mOriginalFragment).commit();
                break;
        }
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!enableExit) {
                enableExit = true;
                Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息
                mHandler.sendEmptyMessageDelayed(0, 3000);
            } else {
                ActivityController.exitApp();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
