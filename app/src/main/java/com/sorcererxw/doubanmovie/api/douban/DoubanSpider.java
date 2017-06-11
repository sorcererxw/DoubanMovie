package com.sorcererxw.doubanmovie.api.douban;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import timber.log.Timber;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/11
 */

public class DoubanSpider {
    private static final String USER_AGENT =
            "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";

    public static String getCelebritySummary(String id) {
        try {
            Document document =
                    Jsoup.connect(String.format("https://movie.douban.com/celebrity/%s/", id))
                            .header("User-Agent", USER_AGENT)
                            .timeout(3000)
                            .get();
            Element element = document.getElementById("wrapper")
                    .getElementById("content")
                    .getElementsByClass("grid-16-8 clearfix").get(0)
                    .getElementsByClass("article").get(0)
                    .getElementById("intro").getElementsByClass("bd").get(0);
            if (element.getElementsByTag("span").size() > 0) {
                return element.getElementsByClass("all hidden").get(0).html();
            } else {
                return element.html();
            }
        } catch (Exception e) {
            Timber.e(e);
            return "";
        }
    }
}
