package com.sorcererxw.doubanmovie.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.data.SimpleMovieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description: 首页内每一项榜单内的横向列表适配器
 * @author: Sorcerer
 * @date: 2017/6/7
 */

public class MainListsItemAdapter extends RecyclerView.Adapter<MainListsItemAdapter.ViewHolder> {
    private Context mContext;

    private List<SimpleMovieBean> mList;

    public MainListsItemAdapter(Context context,
                                List<SimpleMovieBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(mContext)
                        .inflate(R.layout.item_main_lists_item, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SimpleMovieBean movie = mList.get(position);

        Glide.with(mContext)
                .load(movie.getImageUrl())
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(holder.mPicture);

        holder.mTitle.setText(movie.getTitle());
        holder.itemView.setOnClickListener(v -> {
//            mContext.startActivity();
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView_item_main_lists_item)
        ImageView mPicture;

        @BindView(R.id.textView_item_main_lists_item_title)
        TextView mTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
