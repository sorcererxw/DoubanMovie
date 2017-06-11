package com.sorcererxw.doubanmovie.ui.activities;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jaeger.library.StatusBarUtil;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.materialize.util.UIUtils;
import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.api.douban.DoubanClient;
import com.sorcererxw.doubanmovie.data.CelebrityBean;
import com.sorcererxw.doubanmovie.data.SimpleCelebrityBean;
import com.sorcererxw.doubanmovie.ui.adapters.MovieHorizontalListAdapter;
import com.sorcererxw.doubanmovie.utils.ColorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class CelebrityActivity extends AppCompatActivity {

    @BindView(R.id.imageView_celebrity_photo)
    ImageView mPhoto;

    @BindView(R.id.imageView_celebrity_bg)
    ImageView mBg;

    @BindView(R.id.toolbar_celebrity)
    Toolbar mToolbar;

    @BindView(R.id.collapsingToolbarLayout_celebrity)
    CollapsingToolbarLayout mCollapsingToolbarLayout;

    @BindView(R.id.textView_celebrity_name)
    TextView mName;

    @BindView(R.id.textView_celebrity_name_en)
    TextView mEnName;

    @BindView(R.id.cardView_celebrity_work)
    CardView mWorkCard;

    @BindView(R.id.textView_celebrity_info)
    TextView mInfo;

    @BindView(R.id.recyclerView_celebrity_work)
    RecyclerView mRecyclerView;

    private CelebrityBean mCelebrity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity);
        ButterKnife.bind(this);

        SimpleCelebrityBean celebrity = getIntent().getParcelableExtra("celebrity");
        Glide.with(this).load(celebrity.getImageUrl()).into(mPhoto);
        Glide.with(this).load(celebrity.getImageUrl())
                .transition(new DrawableTransitionOptions().crossFade(1000)).into(mBg);

        mName.setText(celebrity.getName());

        StatusBarUtil.setTransparent(this);

        setSupportActionBar(mToolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(celebrity.getName());


        Glide.with(this).asBitmap().load(celebrity.getImageUrl()).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource,
                                        Transition<? super Bitmap> transition) {
                tintToolbar(resource);
            }
        });
        DoubanClient.getInstance().celebrity(celebrity.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(celebrityBean -> {
                    mCelebrity = celebrityBean;
                    init();
                    if (!mEnName.getText().toString().isEmpty()
                            && !mEnName.getText().toString().equals(mName.getText().toString())) {
                        mEnName.animate().alpha(1).start();
                    } else {
                        mEnName.setVisibility(View.GONE);
                    }
                    if (!mInfo.getText().toString().isEmpty()) {
                        mInfo.animate().alpha(1).start();
                    }

                    mWorkCard.setTranslationY(mWorkCard.getHeight());
                    mWorkCard.animate().alpha(1).translationY(0).setDuration(500)
                            .setInterpolator(new AccelerateDecelerateInterpolator()).start();
                }, Timber::e);
    }

    private void init() {
        mEnName.setText(mCelebrity.getNameEn());

        String info = "";
        if (!mCelebrity.getGender().isEmpty()) {
            info = info + "<b>" + getString(R.string.celebrity_gender) + ": </b>"
                    + mCelebrity.getGender() + "<br/>";
        }
        if (!mCelebrity.getBornPlace().isEmpty()) {
            info = info + "<b>" + getString(R.string.celebrity_born_place) + ": </b>"
                    + mCelebrity.getBornPlace() + "<br/>";
        }
        if (!info.isEmpty()) {
            mInfo.setText(Html.fromHtml(info));
        }

        mRecyclerView.setAdapter(new MovieHorizontalListAdapter(this, mCelebrity.getMovieList()));
        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                int pos = parent.getChildAdapterPosition(view);
                if (pos == 0) {
                    outRect.right = (int) UIUtils.convertDpToPixel(4, CelebrityActivity.this);
                    outRect.left = (int) UIUtils.convertDpToPixel(16, CelebrityActivity.this);
                } else if (pos == state.getItemCount() - 1) {
                    outRect.right = (int) UIUtils.convertDpToPixel(16, CelebrityActivity.this);
                    outRect.left = (int) UIUtils.convertDpToPixel(4, CelebrityActivity.this);
                } else {
                    outRect.right = (int) UIUtils.convertDpToPixel(4, CelebrityActivity.this);
                    outRect.left = (int) UIUtils.convertDpToPixel(4, CelebrityActivity.this);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void tintToolbar(Bitmap bitmap) {
        new Palette.Builder(bitmap).generate(palette -> {
            Palette.Swatch swatch = Stream.of(new Palette.Swatch[]{
                    palette.getVibrantSwatch(),
                    palette.getLightVibrantSwatch(),
                    palette.getMutedSwatch(),
                    palette.getLightMutedSwatch(),
                    palette.getDarkMutedSwatch(),
                    palette.getDarkVibrantSwatch(),
                    palette.getDominantSwatch()
            })
                    .filter(value -> value != null)
                    .findFirst()
                    .get();
            assert getSupportActionBar() != null;
            if (swatch != null) {
                mCollapsingToolbarLayout
                        .setStatusBarScrimColor(ColorUtil.primaryToPrimaryDark(swatch.getRgb()));
                mCollapsingToolbarLayout
                        .setContentScrimColor(swatch.getRgb());
                mCollapsingToolbarLayout
                        .setBackgroundColor(swatch.getRgb());
                mCollapsingToolbarLayout.setCollapsedTitleTextColor(swatch.getTitleTextColor());
                mToolbar.setTitleTextColor(swatch.getBodyTextColor());
                getSupportActionBar().setHomeAsUpIndicator(
                        new IconicsDrawable(CelebrityActivity.this,
                                GoogleMaterial.Icon.gmd_arrow_back)
                                .sizeDp(16)
                                .color(swatch.getBodyTextColor())
                );
            }
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        });
    }
}
