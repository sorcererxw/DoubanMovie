package com.sorcererxw.doubanmovie.ui.activities;

import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sorcererxw.doubanmovie.R;

import java.util.ArrayList;

public class Top250Activity extends AppCompatActivity {

    ViewPager pager = null;
    PagerTabStrip tabStrip = null;
    ArrayList<View> viewContainer = new ArrayList<>();
    ArrayList<String> titleContainer = new ArrayList<String>();
    public String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top250);
        pager = (ViewPager) this.findViewById(R.id.viewPager);
        tabStrip = (PagerTabStrip) this.findViewById(R.id.tabstrip);
        tabStrip.setDrawFullUnderline(false);
        tabStrip.setTextSpacing(200);

    }
}
