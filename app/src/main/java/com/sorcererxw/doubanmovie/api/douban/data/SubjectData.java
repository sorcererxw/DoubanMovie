package com.sorcererxw.doubanmovie.api.douban.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/3
 */

public class SubjectData extends SimpleSubjectData {

    /**
     * rating : {"max":10,"average":7.4,"stars":"40","min":0}
     * reviews_count : 295
     * wish_count : 15269
     * douban_site :
     * year : 2009
     * images : {"small":"https://img1.doubanio.com/view/movie_poster_cover/ipst/public/p494268647.jpg","large":"https://img1.doubanio.com/view/movie_poster_cover/lpst/public/p494268647.jpg","medium":"https://img1.doubanio.com/view/movie_poster_cover/spst/public/p494268647.jpg"}
     * alt : https://movie.douban.com/subject/1764796/
     * id : 1764796
     * mobile_url : https://movie.douban.com/subject/1764796/mobile
     * title : 机器人9号
     * do_count : null
     * share_url : https://m.douban.com/movie/subject/1764796
     * seasons_count : null
     * schedule_url :
     * episodes_count : null
     * countries : ["美国"]
     * genres : ["动画","冒险","奇幻"]
     * collect_count : 72549
     * casts : [{"alt":"https://movie.douban.com/celebrity/1054395/","avatars":{"small":"https://img1.doubanio.com/img/celebrity/small/51597.jpg","large":"https://img1.doubanio.com/img/celebrity/large/51597.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/51597.jpg"},"name":"伊利亚·伍德","id":"1054395"},{"alt":"https://movie.douban.com/celebrity/1016673/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/3996.jpg","large":"https://img3.doubanio.com/img/celebrity/large/3996.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/3996.jpg"},"name":"詹妮弗·康纳利","id":"1016673"},{"alt":"https://movie.douban.com/celebrity/1017907/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/55994.jpg","large":"https://img3.doubanio.com/img/celebrity/large/55994.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/55994.jpg"},"name":"约翰·C·赖利","id":"1017907"},{"alt":"https://movie.douban.com/celebrity/1036321/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/42033.jpg","large":"https://img3.doubanio.com/img/celebrity/large/42033.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/42033.jpg"},"name":"克里斯托弗·普卢默","id":"1036321"}]
     * current_season : null
     * original_title : 9
     * summary : 机器人9号（伊利亚•伍德 Elijah Wood 饰）突然醒来，发现身边的世界充满危机，四处残败，一片末世景象。9号带着一个画有三个奇怪符号的圆形物体逃到街上，幸遇发明家机器人2号（马丁•兰道 Martin Landau 饰）给自己装上了声音，但2号却不幸被机器怪兽抓走。9号找到了老兵1号（克里斯托弗•普卢默 Christopher Plummer 饰）、机械工5号（约翰•雷利 John C. Reilly 饰）、疯癫画家6号（克里斯品•格拉夫 Crispin Glover 饰）和大力士8号（弗雷德•塔塔绍尔 Fred Tatasciore 饰）。9号与5号擅自出行援救2号，危急时被女武士7号（詹妮佛•康纳利 Jennifer Connelly 饰）救下，但无意中9号却令终极机器兽复活。带着自己从哪里来以及生存使命的问题，9号决定想尽办法制服机器兽，拯救全世界……©豆瓣
     * subtype : movie
     * directors : [{"alt":"https://movie.douban.com/celebrity/1276787/","avatars":{"small":"https://img3.doubanio.com/img/celebrity/small/1351678808.44.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1351678808.44.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1351678808.44.jpg"},"name":"申·阿克","id":"1276787"}]
     * comments_count : 11729
     * ratings_count : 58195
     * aka : ["9：末世决战","九","Number 9","机器人9号"]
     */

    @SerializedName("reviews_count")
    private int mReviewsCount;
    @SerializedName("wish_count")
    private int mWishCount;
    @SerializedName("douban_site")
    private String mDoubanSite;
    @SerializedName("mobile_url")
    private String mMobileUrl;
    @SerializedName("do_count")
    private Object mDoCount;
    @SerializedName("share_url")
    private String mShareUrl;
    @SerializedName("seasons_count")
    private Object mSeasonsCount;
    @SerializedName("schedule_url")
    private String mScheduleUrl;
    @SerializedName("episodes_count")
    private Object mEpisodesCount;
    @SerializedName("collect_count")
    private int mCollectCount;
    @SerializedName("current_season")
    private Object mCurrentSeason;
    @SerializedName("summary")
    private String mSummary;
    @SerializedName("comments_count")
    private int mCommentsCount;
    @SerializedName("ratings_count")
    private int mRatingsCount;
    @SerializedName("countries")
    private List<String> mCountries;
    @SerializedName("genres")
    private List<String> mGenres;
    @SerializedName("casts")
    private List<SimpleCelebrityData> mCasts;
    @SerializedName("directors")
    private List<SimpleCelebrityData> mDirectors;
    @SerializedName("aka")
    private List<String> mAka;

    public int getReviewsCount() {
        return mReviewsCount;
    }

    public void setReviewsCount(int reviewsCount) {
        mReviewsCount = reviewsCount;
    }

    public int getWishCount() {
        return mWishCount;
    }

    public void setWishCount(int wishCount) {
        mWishCount = wishCount;
    }

    public String getDoubanSite() {
        return mDoubanSite;
    }

    public void setDoubanSite(String doubanSite) {
        mDoubanSite = doubanSite;
    }


    public String getMobileUrl() {
        return mMobileUrl;
    }

    public void setMobileUrl(String mobileUrl) {
        mMobileUrl = mobileUrl;
    }

    public Object getDoCount() {
        return mDoCount;
    }

    public void setDoCount(Object doCount) {
        mDoCount = doCount;
    }

    public String getShareUrl() {
        return mShareUrl;
    }

    public void setShareUrl(String shareUrl) {
        mShareUrl = shareUrl;
    }

    public Object getSeasonsCount() {
        return mSeasonsCount;
    }

    public void setSeasonsCount(Object seasonsCount) {
        mSeasonsCount = seasonsCount;
    }

    public String getScheduleUrl() {
        return mScheduleUrl;
    }

    public void setScheduleUrl(String scheduleUrl) {
        mScheduleUrl = scheduleUrl;
    }

    public Object getEpisodesCount() {
        return mEpisodesCount;
    }

    public void setEpisodesCount(Object episodesCount) {
        mEpisodesCount = episodesCount;
    }

    public int getCollectCount() {
        return mCollectCount;
    }

    public void setCollectCount(int collectCount) {
        mCollectCount = collectCount;
    }

    public Object getCurrentSeason() {
        return mCurrentSeason;
    }

    public void setCurrentSeason(Object currentSeason) {
        mCurrentSeason = currentSeason;
    }


    public String getSummary() {
        return mSummary;
    }

    public void setSummary(String summary) {
        mSummary = summary;
    }

    public int getCommentsCount() {
        return mCommentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        mCommentsCount = commentsCount;
    }

    public int getRatingsCount() {
        return mRatingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        mRatingsCount = ratingsCount;
    }

    public List<String> getCountries() {
        return mCountries;
    }

    public void setCountries(List<String> countries) {
        mCountries = countries;
    }

    public List<String> getGenres() {
        return mGenres;
    }

    public void setGenres(List<String> genres) {
        mGenres = genres;
    }

    public List<SimpleCelebrityData> getCasts() {
        return mCasts;
    }

    public void setCasts(List<SimpleCelebrityData> casts) {
        mCasts = casts;
    }

    public List<SimpleCelebrityData> getDirectors() {
        return mDirectors;
    }

    public void setDirectors(List<SimpleCelebrityData> directors) {
        mDirectors = directors;
    }

    public List<String> getAka() {
        return mAka;
    }

    public void setAka(List<String> aka) {
        mAka = aka;
    }
}
