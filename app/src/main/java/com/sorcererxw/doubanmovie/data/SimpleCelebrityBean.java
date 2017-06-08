package com.sorcererxw.doubanmovie.data;

import com.sorcererxw.doubanmovie.api.douban.data.SimpleCelebrityData;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/8
 */

public class SimpleCelebrityBean {
    private String mName;
    private String mId;
    private String mImageUrl;

    public static SimpleCelebrityBean from(SimpleCelebrityData celebrity) {
        return new SimpleCelebrityBean(
                celebrity.getId(),
                celebrity.getName(),
                celebrity.getAvatars().getLarge()
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
