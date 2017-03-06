// Generated code from Butter Knife. Do not modify!
package com.lws.juzimi.mvp.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.lws.juzimi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding<T extends MainActivity> implements Unbinder {
  protected T target;

  @UiThread
  public MainActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.mBottomNavigationBar = Utils.findRequiredViewAsType(source, R.id.bottom_navigation_bar, "field 'mBottomNavigationBar'", BottomNavigationBar.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.mBottomNavigationBar = null;

    this.target = null;
  }
}
