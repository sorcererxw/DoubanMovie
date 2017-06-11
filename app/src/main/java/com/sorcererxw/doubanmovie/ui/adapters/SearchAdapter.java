package com.sorcererxw.doubanmovie.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
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
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/11
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private Activity mActivity;
    private List<SimpleMovieBean> mList = new ArrayList<>();

    public SearchAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(
                LayoutInflater.from(mActivity).inflate(R.layout.item_search, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        holder.itemView.startAnimation(alphaAnimation);

        Glide.with(mActivity).load(mList.get(position).getImageUrl())
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(holder.mImage);

        holder.mText.setText(mList.get(position).getTitle());

        if (mList.get(position).getOriginTitle().equals(mList.get(position).getTitle())) {
            holder.mSubtitle.setVisibility(View.GONE);
        } else {
            holder.mSubtitle.setVisibility(View.VISIBLE);
            holder.mSubtitle.setText(mList.get(position).getOriginTitle());
        }

        holder.itemView.setOnClickListener(v -> {

            Intent intent = new Intent(mActivity, MovieDetailActivity.class);
            intent.putExtra("movie", mList.get(position));
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, holder.mImage,
                            mActivity.getString(R.string.transition_name_movie_image));
            ActivityCompat.startActivity(mActivity, intent, options.toBundle());
        });
    }

    public void setData(List<SimpleMovieBean> list) {
        mList = list;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class SearchViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView_item_search)
        ImageView mImage;

        @BindView(R.id.textView_item_search)
        TextView mText;

        @BindView(R.id.textView_item_search_subtitle)
        TextView mSubtitle;

        public SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
