package com.sorcererxw.doubanmovie.api.douban.data;

import com.google.gson.annotations.SerializedName;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/6
 */

public class ImagesData {
    /**
     * small : https://img3.doubanio.com/view/movie_poster_cover/ipst/public/p2459723975.jpg
     * large : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2459723975.jpg
     * medium : https://img3.doubanio.com/view/movie_poster_cover/spst/public/p2459723975.jpg
     */

    @SerializedName("small")
    private String mSmall;
    @SerializedName("large")
    private String mLarge;
    @SerializedName("medium")
    private String mMedium;

    public String getSmall() {
        return mSmall;
    }

    public void setSmall(String small) {
        mSmall = small;
    }

    public String getLarge() {
        return mLarge;
    }

    public void setLarge(String large) {
        mLarge = large;
    }

    public String getMedium() {
        return mMedium;
    }

    public void setMedium(String medium) {
        mMedium = medium;
    }
}
