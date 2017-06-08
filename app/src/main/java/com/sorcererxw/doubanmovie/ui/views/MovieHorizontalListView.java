package com.sorcererxw.doubanmovie.ui.views;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mikepenz.materialize.util.UIUtils;
import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.data.SimpleMovieBean;
import com.sorcererxw.doubanmovie.ui.adapters.MovieHorizontalListAdapter;

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

    @BindView(R.id.progressBar_movie_horizontal_list)
    ProgressBar mProgressBar;

    private MovieHorizontalListAdapter mAdapter;

    public void init(String title,
                     String action,
                     OnActionClickListener onActionClickListener,
                     Observable<List<SimpleMovieBean>> source) {
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

        mAdapter = new MovieHorizontalListAdapter((Activity) view.getContext());
        mRecyclerView.setAdapter(mAdapter);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                if (parent.getChildAdapterPosition(view) == state.getItemCount() - 1) {
                    outRect.right = (int) UIUtils.convertDpToPixel(16, view.getContext());
                    outRect.left = (int) UIUtils.convertDpToPixel(8, view.getContext());
                } else if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.left = (int) UIUtils.convertDpToPixel(16, view.getContext());
                    outRect.right = (int) UIUtils.convertDpToPixel(8, view.getContext());
                } else {
                    outRect.left = (int) UIUtils.convertDpToPixel(8, view.getContext());
                    outRect.right = (int) UIUtils.convertDpToPixel(8, view.getContext());
                }
            }
        });

        source.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieBeen -> {
                    initList(movieBeen);
                    mProgressBar.animate().alpha(0).start();
                }, Timber::e);
    }

    private void initList(List<SimpleMovieBean> movieBeenList) {
        mAdapter.setData(movieBeenList);
        mAdapter.notifyDataSetChanged();
    }
}
