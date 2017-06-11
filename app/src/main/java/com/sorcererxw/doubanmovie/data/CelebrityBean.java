package com.sorcererxw.doubanmovie.data;

import com.annimon.stream.Stream;
import com.sorcererxw.doubanmovie.api.douban.data.CelebrityData;

import java.util.List;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/8
 */

public class CelebrityBean extends SimpleCelebrityBean {
    private String mGender;
    private String mNameEn;
    private String mBornPlace;
    private List<SimpleMovieBean> mMovieList;

    public static CelebrityBean from(CelebrityData celebrity) {
        return new CelebrityBean(
                celebrity.getId(),
                celebrity.getName(),
                celebrity.getAvatars().getLarge(),
                celebrity.getGender(),
                celebrity.getNameEn(),
                celebrity.getBornPlace(),
                Stream.of(celebrity.getWorks()).map(
                        worksBean -> SimpleMovieBean.from(worksBean.getSubject())).toList()
        );
    }

    private CelebrityBean(String id, String name, String imageUrl, String gender, String nameEn,
                          String bornPlace, List<SimpleMovieBean> movieList) {
        super(id, name, imageUrl);
        mGender = gender;
        mNameEn = nameEn;
        mBornPlace = bornPlace;
        mMovieList = movieList;
    }

    public String getGender() {
        return mGender;
    }

    public String getNameEn() {
        return mNameEn;
    }

    public String getBornPlace() {
        return mBornPlace;
    }

    public List<SimpleMovieBean> getMovieList() {
        return mMovieList;
    }
}
