package com.example.chenquan.likebaiduanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private BaiduProgressBar baiduProgressBar;
    private BaiduProgressBar2 baiduProgressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baiduProgressBar = new BaiduProgressBar(this);
        baiduProgressBar = (BaiduProgressBar) findViewById(R.id.bd);
        baiduProgressBar2 = new BaiduProgressBar2(this);
        baiduProgressBar2 = (BaiduProgressBar2) findViewById(R.id.bd2);
    }
}
