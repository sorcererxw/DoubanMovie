package com.sorcererxw.doubanmovie.data;

import com.sorcererxw.doubanmovie.api.douban.data.SimpleSubjectData;

/**
 * @description: 基础电影数据, 仅用于列表展示
 * @author: Sorcerer
 * @date: 2017/6/7
 */

public class SimpleMovieBean {

    private String mId;
    private String mTitle;
    private String mOriginTitle;
    private String mImageUrl;
    private double mRating;
    private String mYear;

    public static SimpleMovieBean from(SimpleSubjectData subject) {
        return new SimpleMovieBean(
                subject.getId(),
                subject.getTitle(),
                subject.getOriginalTitle(),
                subject.getImages().getLarge(),
                subject.getRating().getAverage(),
                subject.getYear()
        );
    }

    SimpleMovieBean(
            String id,
            String title,
            String originTitle,
            String imageUrl,
            double rating,
            String year) {
        mId = id;
        mImageUrl = imageUrl;
        mTitle = title;
        mRating = rating;
        mOriginTitle = originTitle;
        mYear = year;
    }

    SimpleMovieBean() {
    }

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public double getRating() {
        return mRating;
    }
}
