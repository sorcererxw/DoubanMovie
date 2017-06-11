package com.sorcererxw.doubanmovie;

import android.app.Application;

import com.sorcererxw.doubanmovie.utils.NetUtil;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import rx_activity_result2.RxActivityResult;
import timber.log.Timber;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/3
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
        RxActivityResult.register(this);

        try {
            NetUtil.enableSSLSocket();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            Timber.e(e);
        }
    }
}
