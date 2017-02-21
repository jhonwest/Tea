package com.example.tea.Activity;

/**
 * Created by lenovo on 2016/10/14.
 */

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.tea.R;

/**
 * 获取当前版本
 */
public class CopyrightInformationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copyright_information);
        /*获取当前系统的android版本号*/
        int currentapiVersion=android.os.Build.VERSION.SDK_INT;
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(getVersionName());
    }

    /**
     * 获取当前应用的版本号
     * @return
     * @throws Exception
     */
    private String getVersionName()
    {
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = packInfo.versionName;
        return version;
    }

}
