// Generated code from Butter Knife. Do not modify!
package com.lws.juzimi.mvp.ui.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lws.juzimi.R;
import com.victor.loading.rotate.RotateLoading;
import java.lang.IllegalStateException;
import java.lang.Override;

public class JujiDetailActivity_ViewBinding<T extends JujiDetailActivity> implements Unbinder {
  protected T target;

  @UiThread
  public JujiDetailActivity_ViewBinding(T target, View source) {
    this.target = target;

    target.layoutSwipeRefresh = Utils.findRequiredViewAsType(source, R.id.layoutSwipeRefresh, "field 'layoutSwipeRefresh'", SwipeRefreshLayout.class);
    target.listJuzi = Utils.findRequiredViewAsType(source, R.id.listJuzi, "field 'listJuzi'", RecyclerView.class);
    target.rotateloading = Utils.findRequiredViewAsType(source, R.id.rotateloading, "field 'rotateloading'", RotateLoading.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.layoutSwipeRefresh = null;
    target.listJuzi = null;
    target.rotateloading = null;

    this.target = null;
  }
}
