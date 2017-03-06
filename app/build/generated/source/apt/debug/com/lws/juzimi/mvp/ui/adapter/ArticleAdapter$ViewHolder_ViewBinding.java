// Generated code from Butter Knife. Do not modify!
package com.lws.juzimi.mvp.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lws.juzimi.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ArticleAdapter$ViewHolder_ViewBinding<T extends ArticleAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public ArticleAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.imgView = Utils.findRequiredViewAsType(source, R.id.imgView, "field 'imgView'", ImageView.class);
    target.textDesc = Utils.findRequiredViewAsType(source, R.id.textDesc, "field 'textDesc'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imgView = null;
    target.textDesc = null;

    this.target = null;
  }
}
