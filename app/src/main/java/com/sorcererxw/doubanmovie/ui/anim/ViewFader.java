package com.sorcererxw.doubanmovie.ui.anim;

import android.view.View;
import android.view.ViewGroup;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/11
 */

public class ViewFader {
    public void hideContentOf(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setVisibility(View.GONE);
        }
    }

    public void showContent(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            viewGroup.getChildAt(i).setVisibility(View.VISIBLE);
        }
    }
}
