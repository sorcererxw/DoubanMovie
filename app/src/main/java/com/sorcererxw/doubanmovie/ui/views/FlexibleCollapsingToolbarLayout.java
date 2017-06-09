package com.sorcererxw.doubanmovie.ui.views;

import android.content.Context;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.AttributeSet;

import java.lang.reflect.Field;

import timber.log.Timber;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/9
 */

public class FlexibleCollapsingToolbarLayout extends CollapsingToolbarLayout {
    public FlexibleCollapsingToolbarLayout(Context context) {
        super(context);
    }

    public FlexibleCollapsingToolbarLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexibleCollapsingToolbarLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Field mExpandedMarginLeft;
    private Field mExpandedMarginTop;
    private Field mExpandedMarginRight;
    private Field mExpandedMarginBottom;

    {
        try {
            mExpandedMarginLeft =
                    this.getClass().getSuperclass().getDeclaredField("mExpandedMarginLeft");
            mExpandedMarginLeft.setAccessible(true);
            mExpandedMarginTop =
                    this.getClass().getSuperclass().getDeclaredField("mExpandedMarginTop");
            mExpandedMarginTop.setAccessible(true);
            mExpandedMarginRight =
                    this.getClass().getSuperclass().getDeclaredField("mExpandedMarginRight");
            mExpandedMarginRight.setAccessible(true);
            mExpandedMarginBottom =
                    this.getClass().getSuperclass().getDeclaredField("mExpandedMarginBottom");
            mExpandedMarginBottom.setAccessible(true);

        } catch (NoSuchFieldException e) {
            Timber.e(e);
        }
    }

    public void setExpandedMarginLeft(int expandedMarginLeft) {
        try {
            mExpandedMarginLeft.setInt(this, expandedMarginLeft);
        } catch (IllegalAccessException e) {
            Timber.e(e);
        }
    }

    public void setExpandedMarginTop(int expandedMarginTop) {
        try {
            mExpandedMarginTop.setInt(this, expandedMarginTop);
        } catch (IllegalAccessException e) {
            Timber.e(e);
        }
    }

    public void setExpandedMarginRight(int expandedMarginRight) {
        try {
            mExpandedMarginRight.setInt(this, expandedMarginRight);
        } catch (IllegalAccessException e) {
            Timber.e(e);
        }
    }

    public void setExpandedMarginBottom(int expandedMarginBottom) {
        try {
            mExpandedMarginBottom.setInt(this, expandedMarginBottom);
        } catch (IllegalAccessException e) {
            Timber.e(e);
        }
    }
}
