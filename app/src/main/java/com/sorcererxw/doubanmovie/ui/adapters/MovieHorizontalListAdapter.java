package com.sorcererxw.doubanmovie.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.data.MovieBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description: 首页内每一项榜单内的横向列表适配器
 * @author: Sorcerer
 * @date: 2017/6/7
 */

public class MovieHorizontalListAdapter
        extends RecyclerView.Adapter<MovieHorizontalListAdapter.ViewHolder> {
    private Context mContext;

    private List<MovieBean> mList;

    public MovieHorizontalListAdapter(Context context) {
        mContext = context;
        mList = new ArrayList<>();
    }

    public MovieHorizontalListAdapter(Context context,
                                      List<MovieBean> list) {
        mContext = context;
        mList = list;
    }

    public void clear() {
        int size = mList.size();
        mList.clear();
        notifyItemRangeRemoved(0, size - 1);
    }

    public void setData(List<MovieBean> list) {
        mList = list;
    }

//    public void add(MovieBean movieBean) {
//        mList.add(movieBean);
//    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_movie_horizontal_list, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieBean movie = mList.get(position);

        Glide.with(mContext)
                .load(movie.getImageUrl())
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(holder.mPicture);

        holder.mTitle.setText(movie.getTitle());
        holder.itemView.setOnClickListener(v -> {

        });

        if (movie.getRating() > 0) {
            holder.mRatingBar.setVisibility(View.VISIBLE);
            holder.mRatingInfo.setText(movie.getRating() + "");
            holder.mRatingBar.setRating((float) movie.getRating() / 2);
        } else {
            holder.mRatingInfo.setText(mContext.getString(R.string.no_rating));
            holder.mRatingBar.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.linearLayout_item_main_lists_item_container)
        LinearLayout mContainer;

        @BindView(R.id.imageView_item_main_lists_item)
        ImageView mPicture;

        @BindView(R.id.textView_item_main_lists_item_title)
        TextView mTitle;

        @BindView(R.id.ratingBar_item_main_lists_item)
        RatingBar mRatingBar;

        @BindView(R.id.textView_item_main_lists_item_rating_info)
        TextView mRatingInfo;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
