package com.sorcererxw.doubanmovie.ui;

import android.app.Activity;
import android.content.Intent;

import com.sorcererxw.doubanmovie.ui.activities.SearchActivity;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/11
 */

public class Navigator {
    private Activity mActivity;

    public Navigator(Activity activity) {
        mActivity = activity;
    }

    public void toSearch() {
        mActivity.startActivity(new Intent(mActivity, SearchActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION));
    }
}
