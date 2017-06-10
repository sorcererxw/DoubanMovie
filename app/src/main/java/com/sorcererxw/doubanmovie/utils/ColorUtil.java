package com.sorcererxw.doubanmovie.utils;

import android.graphics.Color;

/**
 * @description:
 * @author: Sorcerer
 * @date: 2017/6/10
 */

public class ColorUtil {
    public static int primaryToPrimaryDark(int color) {
        return Color.rgb(
                (int) (Color.red(color) * 1.0 * (0xff - 0x22) / 0xff),
                (int) (Color.green(color) * 1.0 * (0xff - 0x22) / 0xff),
                (int) (Color.blue(color) * 1.0 * (0xff - 0x22) / 0xff)
        );
    }
}
