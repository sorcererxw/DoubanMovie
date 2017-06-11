package com.sorcererxw.doubanmovie.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mancj.materialsearchbar.MaterialSearchBar;
import com.sorcererxw.doubanmovie.R;

import java.util.List;

public class SearchActivity extends AppCompatActivity {

    private List<String> lastSearches;
    private MaterialSearchBar searchBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
    }

}
