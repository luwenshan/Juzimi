// Generated code from Butter Knife. Do not modify!
package com.lws.juzimi.mvp.ui.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.lws.juzimi.R;
import com.lws.juzimi.widget.ShowMaxImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MeituMeijuAdapter$ViewHolder_ViewBinding<T extends MeituMeijuAdapter.ViewHolder> implements Unbinder {
  protected T target;

  @UiThread
  public MeituMeijuAdapter$ViewHolder_ViewBinding(T target, View source) {
    this.target = target;

    target.imageView = Utils.findRequiredViewAsType(source, R.id.image_view_meitu_meiju, "field 'imageView'", ShowMaxImageView.class);
    target.textView = Utils.findRequiredViewAsType(source, R.id.text_view_meitu_meiju, "field 'textView'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    T target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");

    target.imageView = null;
    target.textView = null;

    this.target = null;
  }
}
