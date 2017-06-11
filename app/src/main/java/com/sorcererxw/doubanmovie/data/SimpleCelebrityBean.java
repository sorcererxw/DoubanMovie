package com.sorcererxw.doubanmovie.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.sorcererxw.doubanmovie.api.douban.data.SimpleCelebrityData;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/8
 */

public class SimpleCelebrityBean implements Parcelable {
    private String mName;
    private String mId;
    private String mImageUrl;

    protected SimpleCelebrityBean(Parcel in) {
        mName = in.readString();
        mId = in.readString();
        mImageUrl = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mId);
        dest.writeString(mImageUrl);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SimpleCelebrityBean> CREATOR = new Creator<SimpleCelebrityBean>() {
        @Override
        public SimpleCelebrityBean createFromParcel(Parcel in) {
            return new SimpleCelebrityBean(in);
        }

        @Override
        public SimpleCelebrityBean[] newArray(int size) {
            return new SimpleCelebrityBean[size];
        }
    };

    public static SimpleCelebrityBean from(SimpleCelebrityData celebrity) {
        return new SimpleCelebrityBean(
                celebrity.getId(),
                celebrity.getName(),
                celebrity.getAvatars() == null ? "" : celebrity.getAvatars().getLarge()
        );
    }

    public SimpleCelebrityBean(String id, String name, String imageUrl) {
        mName = name;
        mId = id;
        mImageUrl = imageUrl;
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

}
