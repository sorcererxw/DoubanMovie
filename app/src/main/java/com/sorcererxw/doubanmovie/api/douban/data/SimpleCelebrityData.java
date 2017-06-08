package com.sorcererxw.doubanmovie.api.douban.data;

import com.google.gson.annotations.SerializedName;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/8
 */

public class SimpleCelebrityData {
    @SerializedName("alt")
    private String mAlt;
    @SerializedName("avatars")
    private ImagesData mAvatars;
    @SerializedName("name")
    private String mName;
    @SerializedName("id")
    private String mId;

    public String getAlt() {
        return mAlt;
    }

    public void setAlt(String alt) {
        mAlt = alt;
    }

    public ImagesData getAvatars() {
        return mAvatars;
    }

    public void setAvatars(ImagesData avatars) {
        mAvatars = avatars;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }
}
