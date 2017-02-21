package com.example.tea.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS on 2016/10/11.
 */
public class DbUtilsOne extends SQLiteOpenHelper {
    private final static String name="lskshuai.db";
    private final static int version=1;
    public DbUtilsOne(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    public DbUtilsOne(Context context){
        super(context, name, null, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        StringBuilder sql_createTable_person=new StringBuilder("CREATE TABLE IF NOT EXISTS ")
                .append("t_person( ").append("_id").append(" INTEGER PRIMARY KEY AUTOINCREMENT,")
                .append("title").append(" VARCHAR(200) UNIQUE,")
                .append("addr").append(" VARCHAR(200)").append(");");
        //创建表结构
        db.execSQL(sql_createTable_person.toString());
        String sql_insert="INSERT INTO  t_person VALUES(null,?,?);";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
