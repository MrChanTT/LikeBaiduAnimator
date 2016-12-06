package com.example.chenquan.likebaiduanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private BaiduProgressBar baiduProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baiduProgressBar = new BaiduProgressBar(this);
        baiduProgressBar = (BaiduProgressBar) findViewById(R.id.bd);
    }
}
