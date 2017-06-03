package com.sorcererxw.doubanmovie.api.douban.data;

import com.google.gson.annotations.SerializedName;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/6
 */

public class CastsData {
    /**
     * alt : https://movie.douban.com/celebrity/1054435/
     * avatars : {"small":"https://img1.doubanio.com/img/celebrity/small/567.jpg","large":"https://img1.doubanio.com/img/celebrity/large/567.jpg","medium":"https://img1.doubanio.com/img/celebrity/medium/567.jpg"}
     * name : 汤姆·克鲁斯
     * id : 1054435
     */

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
