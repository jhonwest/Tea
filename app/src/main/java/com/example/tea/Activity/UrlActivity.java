package com.example.tea.Activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.tea.R;
import com.example.tea.utils.DbUtils;
import com.example.tea.utils.DbUtilsOne;

public class UrlActivity extends AppCompatActivity {  private WebView webView;
    private ImageButton imgesc, imgefh;
    private String collect;
    private String content, z, title, id, str3;
    private Cursor cursor;
    private DbUtils db;
    private StringBuilder sb;
    private DbUtilsOne dbUtilsOne;
    private SQLiteDatabase sqLiteDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_url);
        dbUtilsOne = new DbUtilsOne(this);
        sqLiteDatabase = dbUtilsOne.getWritableDatabase();

        webView = (WebView) findViewById(R.id.webView);
        imgefh = (ImageButton) findViewById(R.id.imageButton2);
        imgesc = (ImageButton) findViewById(R.id.imageButton);
        sb = new StringBuilder();
        Intent in = this.getIntent();
        final String str = in.getStringExtra("url");

        collect = in.getStringExtra("collect");
        id = in.getStringExtra("id");
        title = in.getStringExtra("title");


        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webView.getSettings().setLoadWithOverviewMode(true);

        webView.loadUrl(str);


        db = new DbUtils(this);


        imgefh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UrlActivity.this,"加入收藏夹",Toast.LENGTH_SHORT).show();
                str3 = db.read();
            }
        });


    }


}
