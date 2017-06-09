package com.sorcererxw.doubanmovie.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.api.douban.DoubanClient;
import com.sorcererxw.doubanmovie.data.MovieBean;
import com.sorcererxw.doubanmovie.data.SimpleMovieBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MovieDetailActivity extends AppCompatActivity {

//    @BindView(R.id.toolbar_movie_detail)
//    Toolbar mToolbar;

//    @BindView(R.id.imageView_movie_detail_poster)
//    ImageView mPoster;

    @BindView(R.id.textView_movie_detail_summary)
    TextView mSummary;

    @BindView(R.id.progressBar_movie_detail)
    ProgressBar mProgressBar;

    @BindView(R.id.imageView_movie_detail_bg)
    ImageView mBackgroundImage;

    @BindView(R.id.linearLayout_movie_detail_content_container)
    ViewGroup mContentContainer;

    private MovieBean mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        SimpleMovieBean movie = getIntent().getParcelableExtra("movie");
//        Glide.with(this).load(movie.getImageUrl()).into(mPoster);

//        StatusBarUtil.setTranslucent(this);

//        setSupportActionBar(mToolbar);

//        new MaterializeBuilder(this)
//                .withTransparentStatusBar(true)
//                .withTranslucentStatusBarProgrammatically(true)
//                .build();

        DoubanClient.getInstance().movie(movie.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(movieBean -> {
                    mMovie = movieBean;
                    mProgressBar.animate().alpha(0).start();
                    mContentContainer.setAlpha(0);
                    init();
                    mContentContainer.animate().alpha(1).start();
                }, Timber::e);

    }

    private void init() {
        mSummary.setText(mMovie.getSummary());

        Glide.with(this).load(mMovie.getImageUrl())
                .transition(new DrawableTransitionOptions().crossFade(1000))
                .into(mBackgroundImage);
    }
}
