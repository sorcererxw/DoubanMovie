package com.sorcererxw.doubanmovie.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.data.SimpleCelebrityBean;
import com.sorcererxw.doubanmovie.ui.activities.CelebrityActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/9
 */

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.CelebrityViewHolder> {

    private List<Pair<SimpleCelebrityBean, String>> mList;
    private Activity mActivity;

    public CelebrityAdapter(Activity activity, List<Pair<SimpleCelebrityBean, String>> list) {
        mActivity = activity;
        mList = list;
    }

    @Override
    public CelebrityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CelebrityViewHolder(
                LayoutInflater.from(mActivity).inflate(R.layout.item_celebrity, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(CelebrityViewHolder holder, int position) {
        SimpleCelebrityBean celebrity = mList.get(position).first;
        Glide.with(mActivity).load(celebrity.getImageUrl())
                .transition(new DrawableTransitionOptions().crossFade(500))
                .into(holder.mPhoto);

        holder.mName.setText(celebrity.getName());
        holder.mRole.setText(mList.get(position).second);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(mActivity, CelebrityActivity.class);
            intent.putExtra("celebrity", celebrity);
            ActivityOptionsCompat options =
                    ActivityOptionsCompat.makeSceneTransitionAnimation(mActivity, holder.mPhoto,
                            mActivity.getString(R.string.transition_name_celebrity_image));
            ActivityCompat.startActivity(mActivity, intent, options.toBundle());
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class CelebrityViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_celebrity_name)
        TextView mName;

        @BindView(R.id.item_celebrity_role)
        TextView mRole;

        @BindView(R.id.imageView_celebrity_photo)
        ImageView mPhoto;

        CelebrityViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
