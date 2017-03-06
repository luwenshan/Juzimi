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

public class OriginalFragment_ViewBinding<T extends OriginalFragment> implements Unbinder {
  protected T target;

  @UiThread
  public OriginalFragment_ViewBinding(T target, View source) {
    this.target = target;

    target.tabLayout = Utils.findRequiredViewAsType(source, R.id.tabLayout, "field 'tabLayout'", TabLayout.class);
    target.viewPager = Utils.findRequiredViewAsType(source, R.id.viewPager, "field 'viewPager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.tabLayout = null;
    target.viewPager = null;

    this.target = null;
  }
}
