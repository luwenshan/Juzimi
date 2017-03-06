// Generated code from Butter Knife. Do not modify!
package com.lws.juzimi.mvp.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lws.juzimi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class OriginalAdapter$ViewHolder_ViewBinding<T extends OriginalAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public OriginalAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.textDesc = Utils.findRequiredViewAsType(source, R.id.textDesc, "field 'textDesc'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.textDesc = null;

    this.target = null;
  }
}
