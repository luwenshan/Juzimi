package com.lws.juzimi.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.lws.juzimi.R;
import com.lws.juzimi.mvp.model.bean.SentenceImageText;
import com.lws.juzimi.util.StringUtil;
import com.lws.juzimi.widget.ShowMaxImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Wenshan.Lu on 2016/12/20.
 * <p>
 * 美图美句
 */

public class MeituMeijuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<SentenceImageText> mDatas;

    private View.OnClickListener mOnItemClickListener;

    private Context mContext;

    private LayoutInflater mLayoutInflater;

    public MeituMeijuAdapter(Context context, List<SentenceImageText> datas, View.OnClickListener onItemClickListener) {
        this.mContext = context;
        this.mDatas = datas;
        this.mOnItemClickListener = onItemClickListener;

        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = mLayoutInflater.inflate(R.layout.list_item_meitu_meiju, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        SentenceImageText itemBean = mDatas.get(position);

        if (itemBean != null) {
            Glide.with(mContext)
                    .load(itemBean.getPic())
                    .asBitmap()
                    .placeholder(R.drawable.load_default_img)
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                    .skipMemoryCache(true)
                    .into(viewHolder.imageView);
            if (StringUtil.isEmpty(itemBean.getDesc())) {
                viewHolder.textView.setVisibility(View.GONE);
            } else {
                viewHolder.textView.setVisibility(View.VISIBLE);
                viewHolder.textView.setText(itemBean.getDesc());
            }

            viewHolder.itemView.setTag(position);
            viewHolder.textView.setOnClickListener(mOnItemClickListener);
        } else {
            Glide.clear(viewHolder.imageView);
            // remove the placeholder (optional)
            viewHolder.imageView.setImageDrawable(null);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_view_meitu_meiju)
        public ShowMaxImageView imageView;
        @BindView(R.id.text_view_meitu_meiju)
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
