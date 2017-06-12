package com.sorcererxw.doubanmovie.ui.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
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
        assert getSupportActionBar() != null;
        mToolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.md_white_1000));
        getSupportActionBar().setHomeAsUpIndicator(
                new IconicsDrawable(this, GoogleMaterial.Icon.gmd_arrow_back)
                        .sizeDp(16).color(ContextCompat.getColor(this, R.color.md_white_1000))
        );

        getSupportActionBar().setTitle(getString(R.string.us_box));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
}