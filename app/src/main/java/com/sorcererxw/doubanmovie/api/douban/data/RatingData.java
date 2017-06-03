package com.sorcererxw.doubanmovie.api.douban.data;

import com.google.gson.annotations.SerializedName;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/6
 */

public class RatingData {
    @SerializedName("max")
    private int mMax;
    @SerializedName("average")
    private double mAverage;
    @SerializedName("stars")
    private String mStars;
    @SerializedName("min")
    private int mMin;

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        mMax = max;
    }

    public double getAverage() {
        return mAverage;
    }

    public void setAverage(double average) {
        mAverage = average;
    }

    public String getStars() {
        return mStars;
    }

    public void setStars(String stars) {
        mStars = stars;
    }

    public int getMin() {
        return mMin;
    }

    public void setMin(int min) {
        mMin = min;
    }
}
