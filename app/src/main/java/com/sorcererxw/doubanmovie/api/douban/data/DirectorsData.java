package com.sorcererxw.doubanmovie.api.douban.data;

import com.google.gson.annotations.SerializedName;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/6
 */

public class DirectorsData {
    /**
     * alt : https://movie.douban.com/celebrity/1276787/
     * avatars : {"small":"https://img3.doubanio.com/img/celebrity/small/1351678808.44.jpg","large":"https://img3.doubanio.com/img/celebrity/large/1351678808.44.jpg","medium":"https://img3.doubanio.com/img/celebrity/medium/1351678808.44.jpg"}
     * name : 申·阿克
     * id : 1276787
     */

    @SerializedName("alt")
    private String mAlt;
    @SerializedName("avatars")
    private AvatarsBeanX mAvatars;
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

    public AvatarsBeanX getAvatars() {
        return mAvatars;
    }

    public void setAvatars(AvatarsBeanX avatars) {
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

    public static class AvatarsBeanX {
        /**
         * small : https://img3.doubanio.com/img/celebrity/small/1351678808.44.jpg
         * large : https://img3.doubanio.com/img/celebrity/large/1351678808.44.jpg
         * medium : https://img3.doubanio.com/img/celebrity/medium/1351678808.44.jpg
         */

        @SerializedName("small")
        private String mSmall;
        @SerializedName("large")
        private String mLarge;
        @SerializedName("medium")
        private String mMedium;

        public String getSmall() {
            return mSmall;
        }

        public void setSmall(String small) {
            mSmall = small;
        }

        public String getLarge() {
            return mLarge;
        }

        public void setLarge(String large) {
            mLarge = large;
        }

        public String getMedium() {
            return mMedium;
        }

        public void setMedium(String medium) {
            mMedium = medium;
        }
    }
}
