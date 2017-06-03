package com.sorcererxw.doubanmovie.ui.views;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.data.MovieBean;
import com.sorcererxw.doubanmovie.ui.adapters.MainListsItemAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/7
 */

public class MovieHorizontalListView extends FrameLayout {
    public MovieHorizontalListView(
            @NonNull Context context) {
        super(context);
    }

    public MovieHorizontalListView(@NonNull Context context,
                                   @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MovieHorizontalListView(@NonNull Context context,
                                   @Nullable AttributeSet attrs,
                                   @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MovieHorizontalListView(@NonNull Context context,
                                   @Nullable AttributeSet attrs,
                                   @AttrRes int defStyleAttr,
                                   @StyleRes int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    interface OnActionClickListener {
        void onClick();
    }

    @BindView(R.id.textView_movie_horizontal_list_title)
    TextView mTitle;

    @BindView(R.id.textView_movie_horizontal_list_action)
    TextView mAction;

    @BindView(R.id.recyclerView_movie_horizontal_list)
    RecyclerView mRecyclerView;

    public void init(String title,
                     String action,
                     OnActionClickListener onActionClickListener,
                     Observable<List<MovieBean>> source) {
        removeAllViews();
        View view = View.inflate(getContext(), R.layout.layout_movie_horizontal_list, null);
        addView(view);
        ButterKnife.bind(this, this);

        mTitle.setText(title);
        mAction.setText(action);
        mAction.setOnClickListener(v -> {
            if (onActionClickListener != null) {
                onActionClickListener.onClick();
            }
        });

        source.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::initList, Timber::e);
    }

    private void initList(List<MovieBean> movieBeenList) {
        MainListsItemAdapter adapter =
                new MainListsItemAdapter(getContext(), movieBeenList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.setHasFixedSize(true);
    }
}
