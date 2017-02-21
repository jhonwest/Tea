package com.example.tea.Activity;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.tea.Fragment.LoadPager1;
import com.example.tea.Fragment.LoadPager2;
import com.example.tea.Fragment.LoadPager3;
import com.example.tea.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView_welcome;

    private ViewPager viewPager_welcome;

    private LinearLayout main_icomLayout;

    private Handler handler_animation;

    private ImageView[] imageViews_icons;

    private ArrayList<Fragment> contentFragments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initViewPager();
        initView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //欢迎页加载动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,2.0f,0,2.0f, Animation.RELATIVE_TO_SELF,0.5F,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setDuration(3000);
        imageView_welcome.startAnimation(scaleAnimation);
        handler_animation = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //通过接受消息判断动作，1则将welcome变成完全透明，2则让welcome消失
                switch (msg.what){
                    case 1:
                        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f,0.0f);
                        alphaAnimation.setDuration(1000);
                        imageView_welcome.setAnimation(alphaAnimation);
                        break;
                    case 2:
                        imageView_welcome.setVisibility(View.GONE);
                        break;
                }
            }
        };

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            handler_animation.sendEmptyMessage(1);
                            Log.e("===Resume==","AlphaAnimation");
                            Thread.sleep(1000);
                            handler_animation.sendEmptyMessage(2);
                            Log.e("===Resume==","ViewGone");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        ).start();

    }

    private void initViewPager(){
        contentFragments = new ArrayList<Fragment>();
        LoadPager1 loadPager1 = new LoadPager1();
        LoadPager2 loadPager2 = new LoadPager2();
        LoadPager3 loadPager3 = new LoadPager3();

        contentFragments.add(loadPager1);
        contentFragments.add(loadPager2);
        contentFragments.add(loadPager3);

    }

    private void initView() {
        imageView_welcome = (ImageView) findViewById(R.id.image_welcome);
        viewPager_welcome = (ViewPager) findViewById(R.id.viewPager_loadpager);
        main_icomLayout = (LinearLayout) findViewById(R.id.linearLayout_iconLayout);
        imageViews_icons = new ImageView[contentFragments.size()];
        for(int i = 0;i < imageViews_icons.length;i++){
            imageViews_icons[i] = new ImageView(this);
            //设置参数
            //宽高
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(15,15);
            //距离
            layoutParams.setMargins(20,0,20,0);
            imageViews_icons[i].setLayoutParams(layoutParams);
            imageViews_icons[i].setPadding(20,0,20,0);
            //初始化选中状态
            if(i == 0){
                imageViews_icons[i].setBackgroundResource(R.mipmap.page_now);
            }else{
                imageViews_icons[i].setBackgroundResource(R.mipmap.page);
            }
            main_icomLayout.addView(imageViews_icons[i]);
        }
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager());
        viewPager_welcome.setAdapter(myAdapter);
        viewPager_welcome.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0;i<contentFragments.size();i++){
                    if(i==position){
                        imageViews_icons[i].setBackgroundResource(R.mipmap.page_now);
                    }else{
                        imageViews_icons[i].setBackgroundResource(R.mipmap.page);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return contentFragments.get(position);
        }

        @Override
        public int getCount() {
            return contentFragments.size();
        }
    }


}
