// Generated code from Butter Knife. Do not modify!
package com.lws.juzimi.mvp.ui.fragment;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lws.juzimi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeijuFragment_ViewBinding<T extends MeijuFragment> implements Unbinder {
  protected T target;

  @UiThread
  public MeijuFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.mTabLayout = Utils.findRequiredViewAsType(source, R.id.tab_layout_meiju, "field 'mTabLayout'", TabLayout.class);
    target.mViewPager = Utils.findRequiredViewAsType(source, R.id.view_pager_meiju, "field 'mViewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mTabLayout = null;
    target.mViewPager = null;

    this.target = null;
  }
}
