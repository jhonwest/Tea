package com.example.tea.utils;

/**
 * Created by lenovo on 2016/10/8.
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * 声明数据库管理类
 * @author dell
 *
 */
public class DBManager {

    /**
     * 1.声明静态的本类类型的对象
     */
    private static DBManager instance;


    /**
     * 声明数据库操作类对象
     */
    private static SQLiteDatabase sqLiteDatabase;

    /**
     * 构造函数私有化
     */
    private DBManager(){
        System.out.println("===DBManager()=====");
    }

    /**
     * 返回本类类型的对象
     * @param context 上下文对象
     * @return 本类类型的对象
     */
    public static DBManager getInstance(Context context){
        if(instance==null){
            instance=new DBManager();
        }

        return instance;
    }

    /**
     * 插入数据
     * @param context 上下文对象
     * @param tableName 表名
     * @param nullColumnHack 钩子列
     * @param values contentValues类型的对象
     * @return 插入是否成功
     */
    public boolean insert(Context context,String tableName,String nullColumnHack,ContentValues values){
        try{
            sqLiteDatabase=DBHelper.getInstance(context).getWritableDatabase();
            long rawId=sqLiteDatabase.insert(tableName, nullColumnHack, values);

            return rawId!=-1;
        }finally{
            if(sqLiteDatabase!=null && sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
                sqLiteDatabase=null;
            }
        }

    }

    /**
     * 修改或者插入数据
     * @param context 上下文对象
     * @param tableName 表名
     * @param nullColumnHack 钩子列
     * @param values ContentValues对象
     * @return 插入或者修改是否成功
     */
    public boolean replace(Context context,String tableName,String nullColumnHack,ContentValues values){
        try{
            sqLiteDatabase=DBHelper.getInstance(context).getWritableDatabase();
            long rawId=sqLiteDatabase.replace(tableName, nullColumnHack, values);

            return rawId!=-1;
        }finally{
            if(sqLiteDatabase!=null && sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
                sqLiteDatabase=null;
            }
        }

    }

    /**
     * 删除数据
     * @param context 上下文对象
     * @param tableName 表名
     * @param whereClause where从句
     * @param whereArgs where从句中占位符的值集合
     * @return 删除是否成功
     */
    public boolean delete(Context context,String tableName,String whereClause,String[] whereArgs){
        try{
            sqLiteDatabase=DBHelper.getInstance(context).getWritableDatabase();
            int count=sqLiteDatabase.delete(tableName, whereClause, whereArgs);


            return count>0;
        }finally{
            if(sqLiteDatabase!=null && sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
                sqLiteDatabase=null;
            }
        }

    }

    /**
     * 查询数据库以游标的形式返还
     * @param context 上下文对象
     * @param distinct 是否消除重复列
     * @param table 表名
     * @param columns 列名
     * @param selection where条件
     * @param selectionArgs where条件占位符
     * @param groupBy 分组表达式
     * @param having 筛选表达式
     * @param orderBy 排序表达式
     * @param limit 分页表达式
     * @return 游标数据
     */
    public Cursor queryWithCursor(Context context, boolean distinct, String table,
                                  String[] columns, String selection, String[] selectionArgs,
                                  String groupBy, String having, String orderBy, String limit){

        sqLiteDatabase=DBHelper.getInstance(context).getReadableDatabase();
        Cursor cursor=sqLiteDatabase.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
        return cursor;
    }

    /**
     * 判断指定条件的数据是否存在
     * @param context 上下文对象
     * @param selection 查询语句
     * @param selectionArgs 查询语句中的占位符
     * @return 数据是否存在
     */
    public boolean isExists(Context context,String selection,String[] selectionArgs){
        try{
            sqLiteDatabase=DBHelper.getInstance(context).getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery(selection, selectionArgs);
            return cursor.getCount()>0;
        }finally{
            if(sqLiteDatabase!=null && sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
                sqLiteDatabase=null;
            }
        }

    }

    /**
     * 查询满足条件的数据集合
     * @param context 上下文对象
     * @param selection where从句
     * @param selectionArgs where从句中占位符的值集合
     * @return 满足条件的数据集合
     */
    public List<Map<String,String>> queryWithList(Context context, String selection,
                                                  String[] selectionArgs){
        List<Map<String,String>> maps=null;
        try{
            sqLiteDatabase=DBHelper.getInstance(context).getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery(selection, selectionArgs);
            if(cursor.getCount()>0){
                maps=new ArrayList<Map<String,String>>();
                while(cursor.moveToNext()){
                    Map<String,String> map=new HashMap<String, String>();
                    for(int i=0;i<cursor.getColumnCount();i++){
                        map.put(cursor.getColumnName(i), cursor.getString(i));
                    }
                    maps.add(map);

                }
            }
            return maps;
        }finally{
            if(sqLiteDatabase!=null && sqLiteDatabase.isOpen()){
                sqLiteDatabase.close();
                sqLiteDatabase=null;
            }
        }

    }



}
