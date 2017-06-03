package com.sorcererxw.doubanmovie.api.douban;

import com.sorcererxw.doubanmovie.api.douban.data.ComingSoonData;
import com.sorcererxw.doubanmovie.api.douban.data.InTheatersData;
import com.sorcererxw.doubanmovie.api.douban.data.SubjectData;
import com.sorcererxw.doubanmovie.api.douban.data.Top250Data;
import com.sorcererxw.doubanmovie.api.douban.data.UsBoxData;
import com.sorcererxw.doubanmovie.data.MovieBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/6
 */

public class DoubanClient {
    private static DoubanClient sDoubanClient;

    public static DoubanClient getInstance() {
        if (sDoubanClient == null) {
            sDoubanClient = new DoubanClient();
        }
        return sDoubanClient;
    }

    private DoubanService mDoubanService;

    private DoubanClient() {
        mDoubanService = new Retrofit.Builder()
                .baseUrl("https://api.douban.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(DoubanService.class);
    }

    public Observable<List<SubjectData>> usBox() {
        return mDoubanService.usBox()
                .flatMap(new Function<UsBoxData, ObservableSource<UsBoxData.SubjectsBean>>() {
                    @Override
                    public ObservableSource<UsBoxData.SubjectsBean> apply(
                            @NonNull UsBoxData usBoxData)
                            throws Exception {
                        return Observable.fromIterable(usBoxData.getSubjects());
                    }
                })
                .flatMap(new Function<UsBoxData.SubjectsBean, ObservableSource<SubjectData>>() {
                    @Override
                    public ObservableSource<SubjectData> apply(
                            @NonNull UsBoxData.SubjectsBean subjectsBean)
                            throws Exception {
                        return mDoubanService.subject(subjectsBean.getSubject().getId());
                    }
                })
                .toList()
                .toObservable();
    }

    public Observable<List<MovieBean>> inTheaters() {
        return mDoubanService.inTheaters()
                .flatMap(
                        new Function<InTheatersData, ObservableSource<InTheatersData.SubjectsBean>>() {
                            @Override
                            public ObservableSource<InTheatersData.SubjectsBean> apply(
                                    @NonNull InTheatersData inTheatersData)
                                    throws Exception {
                                return Observable.fromIterable(inTheatersData.getSubjects());
                            }
                        })
                .map(MovieBean::from)
                .toList()
                .toObservable();
    }

    public Observable<List<SubjectData>> top250(int start, int count) {
        return mDoubanService.top250(start, count)
                .flatMap(new Function<Top250Data, ObservableSource<Top250Data.SubjectsBean>>() {
                    @Override
                    public ObservableSource<Top250Data.SubjectsBean> apply(
                            @NonNull Top250Data top250Data)
                            throws Exception {
                        return Observable.fromIterable(top250Data.getSubjects());
                    }
                })
                .flatMap(new Function<Top250Data.SubjectsBean, ObservableSource<SubjectData>>() {
                    @Override
                    public ObservableSource<SubjectData> apply(
                            @NonNull Top250Data.SubjectsBean subjectsBean)
                            throws Exception {
                        return mDoubanService.subject(subjectsBean.getId());
                    }
                })
                .toList()
                .toObservable();
    }

    public Observable<List<SubjectData>> comingSoon(int start, int count) {
        return mDoubanService.comingSoon(start, count)
                .flatMap(
                        new Function<ComingSoonData, ObservableSource<ComingSoonData.SubjectsBean>>() {
                            @Override
                            public ObservableSource<ComingSoonData.SubjectsBean> apply(
                                    @NonNull ComingSoonData comingSoonData)
                                    throws Exception {
                                return Observable.fromIterable(comingSoonData.getSubjects());
                            }
                        })
                .flatMap(
                        new Function<ComingSoonData.SubjectsBean, ObservableSource<SubjectData>>() {
                            @Override
                            public ObservableSource<SubjectData> apply(
                                    @NonNull ComingSoonData.SubjectsBean subjectsBean)
                                    throws Exception {
                                return mDoubanService.subject(subjectsBean.getId());
                            }
                        })
                .toList()
                .toObservable();
    }
}
