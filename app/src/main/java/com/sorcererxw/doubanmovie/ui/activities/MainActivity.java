package com.sorcererxw.doubanmovie.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.api.douban.DoubanClient;
import com.sorcererxw.doubanmovie.ui.views.ExposedSearchToolbar;
import com.sorcererxw.doubanmovie.ui.views.MovieHorizontalListView;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindViews({
                       R.id.movieHorizontalListView_main_comingsoon,
                       R.id.movieHorizontalListView_main_intheaters,
                       R.id.movieHorizontalListView_main_usbox,
                       R.id.movieHorizontalListView_main_top250,
               })
    MovieHorizontalListView[] mViews;

    @BindView(R.id.movieHorizontalListView_main_intheaters)
    MovieHorizontalListView mInTheaterView;

    @BindView(R.id.movieHorizontalListView_main_comingsoon)
    MovieHorizontalListView mComingSoonView;

    @BindView(R.id.movieHorizontalListView_main_top250)
    MovieHorizontalListView mTop250View;

    @BindView(R.id.movieHorizontalListView_main_usbox)
    MovieHorizontalListView mUsBox;

    @BindView(R.id.exposedSearchToolbar)
    ExposedSearchToolbar mSearchToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mSearchToolbar);
        mSearchToolbar.setTitle(R.string.app_name);
        mSearchToolbar.setOnClickListener(v -> {

        });
        assert getSupportActionBar() != null;

        getSupportActionBar().setHomeAsUpIndicator(
                new IconicsDrawable(this, GoogleMaterial.Icon.gmd_search)
                        .sizeDp(16)
                        .color(ContextCompat.getColor(this, R.color.md_grey_700)));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mInTheaterView.init(getString(R.string.in_theaters), getString(R.string.action_more),
                () -> {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, JustNowActivity.class);
                    MainActivity.this.startActivity(intent);
                }, DoubanClient.getInstance().inTheaters());
        mComingSoonView.init(getString(R.string.coming_soon), getString(R.string.action_more), () -> {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, ComingSoonActivity.class);
                    MainActivity.this.startActivity(intent);
                },
                DoubanClient.getInstance().comingSoon(0, 10));
        mTop250View.init(getString(R.string.top250), getString(R.string.action_more), null,
                DoubanClient.getInstance().top250(0, 10));
        mUsBox.init(getString(R.string.us_box), getString(R.string.action_more), () -> {
                    Intent intent = new Intent();
                    intent.setClass(MainActivity.this, UsRankingActivity.class);
                    MainActivity.this.startActivity(intent);
                },
                DoubanClient.getInstance().usBox());

        new Handler(getMainLooper()).postDelayed(() -> {
            for (int i = 0; i < mViews.length; i++) {
                mViews[i].setTranslationY(mViews[i].getHeight());
                mViews[i].animate().alpha(1).translationY(0).setStartDelay(i * 200)
                        .setInterpolator(new AccelerateDecelerateInterpolator())
                        .setDuration(400).start();
            }
        }, 500);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
