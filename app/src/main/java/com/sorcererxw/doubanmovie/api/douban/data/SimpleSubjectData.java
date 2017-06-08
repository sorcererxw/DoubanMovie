package com.sorcererxw.doubanmovie.api.douban.data;

import com.google.gson.annotations.SerializedName;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/8
 */

public class SimpleSubjectData {
    @SerializedName("id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("alt")
    private String mAlt;
    @SerializedName("images")
    private ImagesData mImages;
    @SerializedName("rating")
    private RatingData mRating;
    @SerializedName("year")
    private String mYear;
    @SerializedName("subtype")
    private String mSubtype;

    public RatingData getRating() {
        return mRating;
    }

    public void setRating(RatingData rating) {
        mRating = rating;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public String getOriginalTitle() {
        return mOriginalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        mOriginalTitle = originalTitle;
    }

    public String getSubtype() {
        return mSubtype;
    }

    public void setSubtype(String subtype) {
        mSubtype = subtype;
    }

    public String getYear() {
        return mYear;
    }

    public void setYear(String year) {
        mYear = year;
    }

    public ImagesData getImages() {
        return mImages;
    }

    public void setImages(ImagesData images) {
        mImages = images;
    }

    public String getAlt() {
        return mAlt;
    }

    public void setAlt(String alt) {
        mAlt = alt;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
