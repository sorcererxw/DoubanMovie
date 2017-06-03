package com.sorcererxw.doubanmovie.data;

import com.sorcererxw.doubanmovie.api.douban.data.ComingSoonData;
import com.sorcererxw.doubanmovie.api.douban.data.InTheatersData;
import com.sorcererxw.doubanmovie.api.douban.data.SubjectData;
import com.sorcererxw.doubanmovie.api.douban.data.Top250Data;
import com.sorcererxw.doubanmovie.api.douban.data.UsBoxData;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/7
 */

public class MovieBean {

    private String mId;
    private String mTitle;
    private String mImageUrl;
    private double mRating;

    public static MovieBean from(SubjectData subject) {
        return new MovieBean(
                subject.getId(),
                subject.getTitle(),
                subject.getImages().getLarge(),
                subject.getRating().getAverage()
        );
    }

    public static MovieBean from(Top250Data.SubjectsBean subject) {
        return new MovieBean(
                subject.getId(),
                subject.getTitle(),
                subject.getImages().getLarge(),
                subject.getRating().getAverage()
        );
    }

    public static MovieBean from(InTheatersData.SubjectsBean subject) {
        return new MovieBean(
                subject.getId(),
                subject.getTitle(),
                subject.getImages().getLarge(),
                subject.getRating().getAverage()
        );
    }

    public static MovieBean from(ComingSoonData.SubjectsBean subject) {
        return new MovieBean(
                subject.getId(),
                subject.getTitle(),
                subject.getImages().getLarge(),
                subject.getRating().getAverage()
        );
    }

    public static MovieBean from(UsBoxData.SubjectsBean subject) {
        return new MovieBean(
                subject.getSubject().getId(),
                subject.getSubject().getTitle(),
                subject.getSubject().getImages().getLarge(),
                subject.getSubject().getRating().getAverage()
        );
    }

    private MovieBean(String id, String title, String imageUrl, double rating) {
        mId = id;
        mImageUrl = imageUrl;
        mTitle = title;
        mRating = rating;
    }

    private MovieBean() {
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
