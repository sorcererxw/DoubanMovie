package com.sorcererxw.doubanmovie.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.api.douban.DoubanClient;
import com.sorcererxw.doubanmovie.data.SimpleMovieBean;
import com.sorcererxw.doubanmovie.ui.adapters.UsRankingAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class UsRankingActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @BindView(R.id.us_ranking_list)
    RecyclerView list;

    @BindView(R.id.toolbar_us_ranking)
    Toolbar mToolbar;

    private UsRankingAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_us_ranking);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        Observable<List<SimpleMovieBean>> source = DoubanClient.getInstance().usBox();
        source.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieBeen -> {
//                    Timber.d(movieBeen.size() + "");
                    mAdapter = new UsRankingAdapter(this, movieBeen);
                    list.setLayoutManager(
                            new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                    list.setAdapter(mAdapter);
                }, Timber::e);
        getSupportActionBar().setTitle(getString(R.string.us_box));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}