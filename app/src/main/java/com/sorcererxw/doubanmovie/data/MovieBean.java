package com.sorcererxw.doubanmovie.data;

import com.sorcererxw.doubanmovie.api.douban.data.InTheatersData;
import com.sorcererxw.doubanmovie.api.douban.data.SubjectData;
import com.sorcererxw.doubanmovie.api.douban.data.Top250Data;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/7
 */

public class MovieBean {

    private String mId;
    private String mTitle;
    private String mImageUrl;

    public static MovieBean from(SubjectData subject) {
        MovieBean movieBean = new MovieBean();
        movieBean.mId = subject.getId();
        movieBean.mImageUrl = subject.getImages().getLarge();
        movieBean.mTitle = subject.getTitle();
        return movieBean;
    }

    public static MovieBean from(Top250Data.SubjectsBean subject) {
        MovieBean movieBean = new MovieBean();
        movieBean.mId = subject.getId();
        movieBean.mImageUrl = subject.getImages().getLarge();
        movieBean.mTitle = subject.getTitle();
        return movieBean;
    }

    public static MovieBean from(InTheatersData.SubjectsBean subject) {
        MovieBean movieBean = new MovieBean();
        movieBean.mId = subject.getId();
        movieBean.mTitle = subject.getTitle();
        movieBean.mImageUrl = subject.getImages().getLarge();
        return movieBean;
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
}
