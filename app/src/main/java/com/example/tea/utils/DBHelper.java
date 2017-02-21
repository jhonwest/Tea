package com.example.tea.utils;

/**
 * Created by lenovo on 2016/10/8.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tea.common.Const_Collect;
import com.example.tea.common.Const_MainData;
import com.example.tea.common.Const_ViewPagerImg;
import com.example.tea.common.DBInfo;

/**
 * 自定义数据库辅助类,采用单例设计模式
 * @author dell
 *
 */
public class DBHelper extends SQLiteOpenHelper {

    /**
     * 1.声明静态的本类类型的对象
     */
    private static  DBHelper instance;

    /**
     * 2.构造函数私有化
     * @param context
     */
    private DBHelper(Context context) {
        super(context, DBInfo.DATABASE_NAME, null, DBInfo.DATABASE_VERSION);
        // TODO Auto-generated constructor stub
    }

    /**
     * 3.通过公有的静态方法返回本类类型的对象
     * @param context 上下文对象
     * @return DBHelper类型的对象
     */
    public static DBHelper getInstance(Context context){
        if(instance==null){
            instance=new DBHelper(context);
        }

        return instance;
    }

    /**
     * UNIQUE 是关键字，使用replace会进行判断，没有就插入，有就修改
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_main_data="CREATE TABLE IF NOT EXISTS "+Const_MainData.TABLE_NAME_MAIN_DATA+
                " ( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Const_MainData.TABLE_NAME_MAIN_DATA_FIELD_ID+" INTEGER UNIQUE NOT NULL,"+
                Const_MainData.TABLE_NAME_MAIN_DATA_FIELD_TITLE+" VARCHAR(50),"+
                Const_MainData.TABLE_NAME_MAIN_DATA_FIELD_SOURCE+" VARCHAR(50),"+
                Const_MainData.TABLE_NAME_MAIN_DATA_FIELD_DESCRIPTION+" VARCHAR(50),"+
                Const_MainData.TABLE_NAME_MAIN_DATA_FIELD_WAP_THUMB+" VARCHAR(50),"+
                Const_MainData.TABLE_NAME_MAIN_DATA_FIELD_CREATE_TIME+" VARCHAR(50),"+
                Const_MainData.TABLE_NAME_MAIN_DATA_FIELD_NICKNAME+" VARCHAR(50),"+
                Const_MainData.TABLE_NAME_MAIN_DATA_FIELD_CATEGORY+" VARCHAR(50)"+
                ")";

        db.execSQL(sql_main_data);
        System.out.println("sql_main_data:"+sql_main_data);

        //insert into t_table  values(1,'hsj',20);
        //insert into t_table  values(1,'hsj',20);
        String sql_viewpager_img="CREATE TABLE IF NOT EXISTS "+Const_ViewPagerImg.TABLE_NAME_VIEWPAGER_IMG+
                "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Const_ViewPagerImg.TABLE_NAME_FIELD_ID+" INTEGER UNIQUE NOT NULL,"+
                Const_ViewPagerImg.TABLE_NAME_FIELD_TITLE+" VARCHAR(50),"+
                Const_ViewPagerImg.TABLE_NAME_FIELD_NAME+" VARCHAR(50),"+
                Const_ViewPagerImg.TABLE_NAME_FIELD_LINK+" VARCHAR(50),"+
                Const_ViewPagerImg.TABLE_NAME_FIELD_CONTENT+" VARCHAR(50),"+
                Const_ViewPagerImg.TABLE_NAME_FIELD_IMAGE+" VARCHAR(50),"+
                Const_ViewPagerImg.TABLE_NAME_FIELD_IMAGE_S+" VARCHAR(50)"+
                ");";
        db.execSQL(sql_viewpager_img);
        System.out.println("sql_viewpager_img:"+sql_viewpager_img);

        String sql_collect="CREATE TABLE IF NOT EXISTS "+Const_Collect.TABLE_NAME_MYCOLLECT+
                "( _id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                Const_Collect.TABLE_NAME_MYCOLLECT_FIELD_ID+" INTEGER UNIQUE NOT NULL,"+
                Const_Collect.TABLE_NAME_MYCOLLECT_FIELD_TITLE+" VARCHAR(50),"+
                Const_Collect.TABLE_NAME_MYCOLLECT_FIELD_SOURCE+" VARCHAR(50),"+
                Const_Collect.TABLE_NAME_MYCOLLECT_FIELD_DESCRIPTION+" VARCHAR(50),"+
                Const_Collect.TABLE_NAME_MYCOLLECT_FIELD_WAP_THUMB+" VARCHAR(50),"+
                Const_Collect.TABLE_NAME_MYCOLLECT_FIELD_CREATE_TIME+" VARCHAR(50),"+
                Const_Collect.TABLE_NAME_MYCOLLECT_FIELD_NICKNAME+" VARCHAR(50)"+
                ");";
        db.execSQL(sql_collect);
        System.out.println("sql_collect:"+sql_collect);


        System.out.println("====DBHelper.onCreate(SQLiteDatabase db)===");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("====DBHelper.onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)==");

    }

}

