package com.sorcererxw.doubanmovie.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.sorcererxw.doubanmovie.api.douban.data.SimpleSubjectData;

/**
 * @description: 基础电影数据, 仅用于列表展示
 * @author: Sorcerer
 * @date: 2017/6/7
 */

public class SimpleMovieBean implements Parcelable {

    private String mId;
    private String mTitle;
    private String mOriginTitle;
    private String mImageUrl;
    private double mRating;
    private String mYear;

    protected SimpleMovieBean(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mOriginTitle = in.readString();
        mImageUrl = in.readString();
        mRating = in.readDouble();
        mYear = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mId);
        dest.writeString(mTitle);
        dest.writeString(mOriginTitle);
        dest.writeString(mImageUrl);
        dest.writeDouble(mRating);
        dest.writeString(mYear);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SimpleMovieBean> CREATOR = new Creator<SimpleMovieBean>() {
        @Override
        public SimpleMovieBean createFromParcel(Parcel in) {
            return new SimpleMovieBean(in);
        }

        @Override
        public SimpleMovieBean[] newArray(int size) {
            return new SimpleMovieBean[size];
        }
    };

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

    public String getOriginTitle() {
        return mOriginTitle;
    }

    public String getYear() {
        return mYear;
    }
}
