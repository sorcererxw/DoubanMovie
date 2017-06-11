package com.sorcererxw.doubanmovie.ui.anim;

import android.app.Activity;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.sorcererxw.doubanmovie.R;
import com.sorcererxw.doubanmovie.ui.Navigator;
import com.sorcererxw.doubanmovie.ui.callbacks.SimpleTransitionListener;
import com.sorcererxw.doubanmovie.ui.views.ExposedSearchToolbar;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2016/11/4
 */

public class SearchTransitioner {
    private Activity mActivity;
    private ExposedSearchToolbar mSearchToolbar;
    private Navigator mNavigator;
    private ViewGroup mActivityContent;
    private ViewFader mViewFader;

    private int mToolbarMargin;
    private boolean mTransitioning;

    public SearchTransitioner(Activity activity,
                              Navigator navigator,
                              ViewGroup activityContent,
                              ExposedSearchToolbar searchToolbar,
                              ViewFader viewFader) {
        mActivity = activity;
        mNavigator = navigator;
        mActivityContent = activityContent;
        mSearchToolbar = searchToolbar;
        mViewFader = viewFader;
        mToolbarMargin = activity.getResources().getDimensionPixelSize(R.dimen.padding_tight);
    }

    public void transitionToSearch() {
        if (mTransitioning) {
            return;
        }
        Transition transition = FadeOutTransition.withAction(navigateToSearchWhenDone());
        TransitionManager.beginDelayedTransition(mSearchToolbar, transition);
        expandToolbar();
        mViewFader.hideContentOf(mSearchToolbar);
        mActivityContent.animate().alpha(0).setDuration(250).start();
    }

    private void expandToolbar() {
        FrameLayout.LayoutParams layoutParams =
                (FrameLayout.LayoutParams) mSearchToolbar.getLayoutParams();
        layoutParams.setMargins(0, 0, 0, 0);
        mSearchToolbar.setLayoutParams(layoutParams);
    }

    private Transition.TransitionListener navigateToSearchWhenDone() {
        return new SimpleTransitionListener() {
            @Override
            public void onTransitionStart(Transition transition) {
                mTransitioning = true;
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                mTransitioning = false;
                mNavigator.toSearch();
                mActivity.overridePendingTransition(0, 0);
            }
        };
    }

    public void onActivityResumed() {

        TransitionManager
                .beginDelayedTransition(mSearchToolbar, FadeInTransition.createTransition());
        FrameLayout.LayoutParams layoutParams =
                (FrameLayout.LayoutParams) mSearchToolbar.getLayoutParams();
        layoutParams.setMargins(mToolbarMargin, mToolbarMargin, mToolbarMargin, mToolbarMargin);
        mViewFader.showContent(mSearchToolbar);
        mSearchToolbar.setLayoutParams(layoutParams);
        mActivityContent.animate().alpha(1).setDuration(250).start();
    }

}
