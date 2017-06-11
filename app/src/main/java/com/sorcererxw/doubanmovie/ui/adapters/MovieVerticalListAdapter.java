package com.sorcererxw.doubanmovie.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
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
import com.sorcererxw.doubanmovie.data.SimpleMovieBean;
import com.sorcererxw.doubanmovie.ui.activities.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by JGallon on 2017/6/11.
 */

public class MovieVerticalListAdapter extends RecyclerView.Adapter<MovieVerticalListAdapter.ViewHolder> {

    private Activity mActivity;
    private List<SimpleMovieBean> mList;
    private int mresourceID;

    public MovieVerticalListAdapter(Activity context, int resourceID) {
        mActivity = context;
        mList = new ArrayList<>();
        mresourceID = resourceID;
    }

    public MovieVerticalListAdapter(Activity context, int resourceID,
                                    List<SimpleMovieBean> list) {
        mActivity = context;
        mresourceID = resourceID;
        mList = list;
    }

    public void setData(List<SimpleMovieBean> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mActivity)
                .inflate(mresourceID, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SimpleMovieBean movie = mList.get(position);

//        runEnterAnimation(holder.itemView, position);

        Glide.with(mActivity)
                .load(movie.getImageUrl())
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(holder.mPicture);

        holder.mTitle.setText(movie.getTitle());
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, MovieDetailActivity.class);
            intent.putExtra("movie", movie);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, holder.mPicture,
                            mActivity.getString(R.string.transition_name_movie_image));
            ActivityCompat.startActivity(mActivity, intent, options.toBundle());
        });

        if (movie.getRating() > 0) {
            holder.mRatingBar.setVisibility(View.VISIBLE);
            holder.mRatingInfo.setText(String.valueOf(movie.getRating()));
            holder.mRatingBar.setRating((float) movie.getRating() / 2);
        } else {
            holder.mRatingInfo.setText("评价人数不足");
            holder.mRatingBar.setVisibility(View.GONE);
        }
    }

    private int mLastAnimatedPosition = -1;

//    private void runEnterAnimation(View view, int position) {
//        if (position > mLastAnimatedPosition) {
//            mLastAnimatedPosition = position;
//            view.setAlpha(0);
//            view.animate()
//                    .alpha(1)
//                    .setStartDelay(200 * position)
//                    .setInterpolator(new DecelerateInterpolator(3.f))
//                    .setDuration(500)
//                    .start();
//        }
//    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.linearLayout_item_just_now_item_container)
        LinearLayout mContainer;

        @BindView(R.id.imageView_item_just_now)
        ImageView mPicture;

        @BindView(R.id.title_item_just_now)
        TextView mTitle;

        @BindView(R.id.ratingBar_item_just_now)
        RatingBar mRatingBar;

        @BindView(R.id.textView_item_just_now_rating_info)
        TextView mRatingInfo;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
