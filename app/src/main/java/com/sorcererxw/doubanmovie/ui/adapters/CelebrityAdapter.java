package com.sorcererxw.doubanmovie.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.sorcererxw.doubanmovie.data.SimpleCelebrityBean;

import java.util.List;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/9
 */

public class CelebrityAdapter extends RecyclerView.Adapter<CelebrityAdapter.CelebrityViewHolder> {
    private List<SimpleCelebrityBean> mList;
    private Context mContext;

    public CelebrityAdapter(Context context, List<SimpleCelebrityBean> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public CelebrityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(CelebrityViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    static class CelebrityViewHolder extends RecyclerView.ViewHolder {

        public CelebrityViewHolder(View itemView) {
            super(itemView);
        }
    }
}
