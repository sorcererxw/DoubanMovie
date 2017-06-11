package com.sorcererxw.doubanmovie.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.jaeger.library.StatusBarUtil;
import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.api.douban.DoubanClient;
import com.sorcererxw.doubanmovie.data.SimpleMovieBean;
import com.sorcererxw.doubanmovie.ui.adapters.SearchAdapter;
import com.sorcererxw.doubanmovie.ui.anim.FadeInTransition;
import com.sorcererxw.doubanmovie.ui.anim.FadeOutTransition;
import com.sorcererxw.doubanmovie.ui.anim.ViewFader;
import com.sorcererxw.doubanmovie.ui.callbacks.SimpleTransitionListener;
import com.sorcererxw.doubanmovie.ui.views.SearchBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.searchBar)
    SearchBar mSearchBar;

    @BindView(R.id.viewGroup_search_content_container)
    ViewGroup mContent;

    @BindView(R.id.recyclerView_search)
    RecyclerView mRecyclerView;

    private ViewFader mViewFader = new ViewFader();

    private SearchAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(mSearchBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
        if (savedInstanceState == null) {
            mViewFader.hideContentOf(mSearchBar);
            ViewTreeObserver viewTreeObserver = mSearchBar.getViewTreeObserver();
            viewTreeObserver
                    .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            mSearchBar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            animateShowSearch();
                        }

                        private void animateShowSearch() {
                            TransitionManager.beginDelayedTransition(mSearchBar,
                                    FadeInTransition.createTransition());
                            mContent.animate().alpha(1).setDuration(250).start();

                            mViewFader.showContent(mSearchBar);
                        }
                    });
        } else {
            mContent.animate().alpha(1).setDuration(250).start();
        }

        StatusBarUtil.setTranslucent(this);

        mSearchBar.setHint(getString(R.string.action_search_movie));
        mSearchBar.postDelayed(() -> mSearchBar.requestEditTextFocus(), 250);

        mAdapter = new SearchAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mSearchBar.setSearchListener(text -> {
            DoubanClient.getInstance().search(text, 0, 20)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<List<SimpleMovieBean>>() {
                        @Override
                        public void onSubscribe(@NonNull Disposable d) {
                            mSearchBar.setSearching(true);
                        }

                        @Override
                        public void onNext(@NonNull List<SimpleMovieBean> simpleMovieBeen) {
                            mSearchBar.setSearching(false);
                            mAdapter.setData(simpleMovieBeen);
                            mAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Timber.e(e);
                            mSearchBar.setSearching(false);
                        }

                        @Override
                        public void onComplete() {
                            mSearchBar.setSearching(false);
                        }
                    });
        });
    }

    @Override
    public void finish() {
        exitTransitionWithAction(() -> {
            SearchActivity.super.finish();
            overridePendingTransition(0, 0);
        });
    }

    private void exitTransitionWithAction(final Runnable endingAction) {
        Transition transition = FadeOutTransition.withAction(new SimpleTransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                endingAction.run();
            }
        });
        TransitionManager.beginDelayedTransition(mSearchBar, transition);
        mViewFader.hideContentOf(mSearchBar);
        TransitionManager.beginDelayedTransition(mContent, new Fade(Fade.OUT));
        mContent.animate().alpha(0).setDuration(250).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
