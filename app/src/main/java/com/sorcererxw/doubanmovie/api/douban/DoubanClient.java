package com.sorcererxw.doubanmovie.api.douban;

import com.sorcererxw.doubanmovie.api.douban.data.ComingSoonData;
import com.sorcererxw.doubanmovie.api.douban.data.InTheatersData;
import com.sorcererxw.doubanmovie.api.douban.data.SearchData;
import com.sorcererxw.doubanmovie.api.douban.data.SimpleSubjectData;
import com.sorcererxw.doubanmovie.api.douban.data.Top250Data;
import com.sorcererxw.doubanmovie.api.douban.data.UsBoxData;
import com.sorcererxw.doubanmovie.data.MovieBean;
import com.sorcererxw.doubanmovie.data.SimpleMovieBean;

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

    public Observable<List<SimpleMovieBean>> usBox() {
        return mDoubanService.usBox()
                .flatMap(new Function<UsBoxData, ObservableSource<UsBoxData.SubjectsBean>>() {
                    @Override
                    public ObservableSource<UsBoxData.SubjectsBean> apply(
                            @NonNull UsBoxData usBoxData)
                            throws Exception {
                        return Observable.fromIterable(usBoxData.getSubjects());
                    }
                })
                .map(UsBoxData.SubjectsBean::getSubject)
                .map(SimpleMovieBean::from)
                .toList()
                .toObservable();
    }

    public Observable<List<SimpleMovieBean>> inTheaters() {
        return mDoubanService.inTheaters()
                .flatMap(
                        new Function<InTheatersData, ObservableSource<SimpleSubjectData>>() {
                            @Override
                            public ObservableSource<SimpleSubjectData> apply(
                                    @NonNull InTheatersData inTheatersData)
                                    throws Exception {
                                return Observable.fromIterable(inTheatersData.getSubjects());
                            }
                        })
                .map(SimpleMovieBean::from)
                .toList()
                .toObservable();
    }

    public Observable<List<SimpleMovieBean>> top250(int start, int count) {
        return mDoubanService.top250(start, count)
                .flatMap(new Function<Top250Data, ObservableSource<SimpleSubjectData>>() {
                    @Override
                    public ObservableSource<SimpleSubjectData> apply(
                            @NonNull Top250Data top250Data)
                            throws Exception {
                        return Observable.fromIterable(top250Data.getSubjects());
                    }
                })
                .map(SimpleMovieBean::from)
                .sorted((o1, o2) -> o1.getRating() >= o2.getRating() ? -1 : 1)
                .toList()
                .toObservable();
    }

    public Observable<List<SimpleMovieBean>> comingSoon(int start, int count) {
        return mDoubanService.comingSoon(start, count)
                .flatMap(
                        new Function<ComingSoonData, ObservableSource<SimpleSubjectData>>() {
                            @Override
                            public ObservableSource<SimpleSubjectData> apply(
                                    @NonNull ComingSoonData comingSoonData)
                                    throws Exception {
                                return Observable.fromIterable(comingSoonData.getSubjects());
                            }
                        })
                .map(SimpleMovieBean::from)
                .toList()
                .toObservable();
    }

    /**
     * @param q     query string
     * @param start start (default 0)
     * @param count count (default 20)
     * @return
     */
    public Observable<List<SimpleMovieBean>> search(String q, int start, int count) {
        return mDoubanService.search(q, start, count)
                .flatMap(new Function<SearchData, ObservableSource<SimpleSubjectData>>() {
                    @Override
                    public ObservableSource<SimpleSubjectData> apply(
                            @NonNull SearchData searchData)
                            throws Exception {
                        return Observable.fromIterable(searchData.getSubjects());
                    }
                })
                .map(SimpleMovieBean::from)
                .toList()
                .toObservable();
    }

    public Observable<MovieBean> movie(String id) {
        return mDoubanService.subject(id).map(MovieBean::from);
    }
}
