package com.sorcererxw.doubanmovie.data;

import com.annimon.stream.Stream;
import com.sorcererxw.doubanmovie.api.douban.data.SubjectData;

import java.util.List;

/**
 * @description: 详细电影数据, 用于详情页面展示
 * @author: Sorcerer
 * @date: 2017/6/8
 */

public class MovieBean extends SimpleMovieBean {
    private String mSummary;
    private List<String> mCountries;
    private List<String> mGenres;
    private List<SimpleCelebrityBean> mCasts;
    private List<SimpleCelebrityBean> mDirectors;
    private List<String> mAka;

    public static MovieBean from(SubjectData subject) {
        return new MovieBean(
                subject.getId(),
                subject.getTitle(),
                subject.getOriginalTitle(),
                subject.getImages().getLarge(),
                subject.getRating().getAverage(),
                subject.getYear(),
                subject.getSummary(),
                subject.getCountries(),
                subject.getGenres(),
                Stream.of(subject.getCasts()).map(SimpleCelebrityBean::from).toList(),
                Stream.of(subject.getDirectors()).map(SimpleCelebrityBean::from).toList(),
                subject.getAka()
        );
    }

    private MovieBean(String id, String title, String originalTitle, String imageUrl, double rating,
                      String year,
                      String summary,
                      List<String> countries,
                      List<String> genres,
                      List<SimpleCelebrityBean> casts,
                      List<SimpleCelebrityBean> directors,
                      List<String> aka) {
        super(id, title, originalTitle, imageUrl, rating, year);
        mSummary = summary;
        mCountries = countries;
        mGenres = genres;
        mCasts = casts;
        mDirectors = directors;
        mAka = aka;
    }

    public String getSummary() {
        return mSummary;
    }

    public List<String> getCountries() {
        return mCountries;
    }

    public List<String> getGenres() {
        return mGenres;
    }

    public List<String> getAka() {
        return mAka;
    }

    public List<SimpleCelebrityBean> getDirectors() {
        return mDirectors;
    }

    public List<SimpleCelebrityBean> getCasts() {
        return mCasts;
    }
}
