package com.sorcererxw.doubanmovie.api.douban;

import com.sorcererxw.doubanmovie.api.douban.data.CelebrityData;
import com.sorcererxw.doubanmovie.api.douban.data.ComingSoonData;
import com.sorcererxw.doubanmovie.api.douban.data.InTheatersData;
import com.sorcererxw.doubanmovie.api.douban.data.SearchData;
import com.sorcererxw.doubanmovie.api.douban.data.SubjectData;
import com.sorcererxw.doubanmovie.api.douban.data.Top250Data;
import com.sorcererxw.doubanmovie.api.douban.data.UsBoxData;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/3
 */

interface DoubanService {

    @GET("/v2/movie/in_theaters")
    Observable<InTheatersData> inTheaters();

    @GET("/v2/movie/coming_soon")
    Observable<ComingSoonData> comingSoon(@Query("start") int start, @Query("count") int count);

    @GET("/v2/movie/us_box")
    Observable<UsBoxData> usBox();

    @GET("/v2/movie/top250")
    Observable<Top250Data> top250(@Query("start") int start, @Query("count") int count);

    @GET("/v2/movie/search")
    Observable<SearchData> search(@Query("q") String q);

    @GET("/v2/movie/celebrity/{id}")
    Observable<CelebrityData> celebrity(@Path("id") String id);

    @GET("/v2/movie/subject/{id}")
    Observable<SubjectData> subject(@Path("id") String id);
}
