package com.example.tea.utils;


import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ASUS on 2016/10/9.
 */
public class DbUtils   {

 private String name;
    private List<String> stringList;
    private Context context;
    private String file;
    private InputStream inputStream = null;

    public DbUtils(Context context) {
        this.context = context;
    }

    public String write(String str) {
        stringList = new ArrayList<>();
        if (isSDCardExists()) {
            //1.得到外部存储根目录的路径对应的File 对象
            File parentFile = Environment.getExternalStorageDirectory();
            // /mnt/sdcard  File file=new File("/mnt/sdcard");
            System.out.println("parentFile=" + parentFile.getAbsolutePath());

            //2.实例化外部存储对应的File对象
            File destFile = new File(parentFile, "shuaikai.bak");

            OutputStream outputStream = null;
            try {
                //3.将文件对象包装成输出流对象,方便写出数据
                outputStream = new FileOutputStream(destFile);
                //4.调用write()方法将字符串数据转换成字节数组写出到目标文件中

                outputStream.write(str.toString().getBytes());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (outputStream != null) {
                    try {
                        //5.关闭输出流对象
                        outputStream.close();
                        outputStream = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("用户手机SDCard不存在或不可用!");
        }
        return null;
    }

    /**
     * 判断手机的SDCard是否存在并且可用
     *
     * @return
     */
    private boolean isSDCardExists() {
        //得到外部存储的状态
        String state = Environment.getExternalStorageState();

        //如果外部存储的状态等于mounted说明外部存储状态存在并且可用
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }

        // Environment.MEDIA_MOUNTED_READ_ONLY:代表外部存储处于只读状态
        // Environment.MEDIA_BAD_REMOVAL:外部存储设备损坏或者已经拔出


        return false;
    }

    /**
     * 读取外部存储设备中的数据
     */
    public String read() {
        StringBuilder sb = new StringBuilder();
        if (this.isSDCardExists()) {
            //1.得到外部存储的根目录对应的文件对象
            File parentFile = Environment.getExternalStorageDirectory();
            //2.得到需要操作的目标文件对象
            File destFile = new File(parentFile, "shuaikai.bak");
            //3.将目标文件封装成输入流对象

            try {
                //4.实例化输入流对象
                inputStream = new FileInputStream(destFile);

                //5.读取数据
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    sb.append(new String(buffer, 0, len));
                }
                 name = sb.toString();



//                System.out.println("name=" + name);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null) {
                    try {
                        //6.关闭输入流对象
                        inputStream.close();
                        inputStream = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("用户手机SDCard不存在或不可用!");
        }

        return name;
    }



    public static void deleteAllFile(){

        String path = Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"shuaikai.bak";

        deleteFile(new File(path));

    }



    public static void deleteFile(File oldPath) {

        if (oldPath.isDirectory()) {

            File[] files = oldPath.listFiles();

            for (File file : files) {

                deleteFile(file);

                file.delete();

            }

        }else{

            oldPath.delete();

        }

    }

}



