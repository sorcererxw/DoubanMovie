package com.sorcererxw.doubanmovie.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.api.douban.DoubanClient;
import com.sorcererxw.doubanmovie.data.SimpleMovieBean;
import com.sorcererxw.doubanmovie.ui.adapters.MovieVerticalListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class JustNowActivity extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @BindView(R.id.just_now_list)
    RecyclerView list;

    @BindView(R.id.toolbar_just_now)
    Toolbar mToolbar;

    private MovieVerticalListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_now);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        Observable<List<SimpleMovieBean>> source = DoubanClient.getInstance().inTheaters();
        source.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieBeen -> {
                    Timber.d(movieBeen.size() + "");
                    mAdapter = new MovieVerticalListAdapter(this, R.id.imageView_item_just_now, movieBeen);
                    list.setAdapter(mAdapter);

//                    initList(movieBeen);
                }, Timber::e);
        getSupportActionBar().setTitle("正在热映");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void initList(List<SimpleMovieBean> movieBeenList) {
        mAdapter.setData(movieBeenList);
        mAdapter.notifyDataSetChanged();
    }
}
