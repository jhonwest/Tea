package com.example.tea.Activity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.BoolRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tea.Fragment.DataFragment;
import com.example.tea.Fragment.EncyclopediaFragment;
import com.example.tea.Fragment.HeadLineFragment;
import com.example.tea.Fragment.InformationFragment;
import com.example.tea.Fragment.OperateFragment;
import com.example.tea.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2016/10/8.
 */
public class IndexActivity extends FragmentActivity implements View.OnClickListener {

    /**
     * 声明抽屉布局组件
     */
    private DrawerLayout drawerLayout_layout;

    /**
     * 声明头条，百科，资讯，经营和数据的TextView
     */
    private TextView textView_headLine, textView_encyclopedia,
            textView_information, textView_operate, textView_data;

    /**
     * 声明头条，百科，资讯，经营和数据下面的ImageView
     */
    private ImageView imageView_choose1, imageView_choose2,
            imageView_choose3, imageView_choose4, imageView_choose5;

    /**
     * 声明"更多"对应的ImageView
     */
    private ImageView imageButton_more;

    /**
     * 声明索引页对应的ViewPager
     */
    private ViewPager viewPager_indexPager;

    /**
     * 声明"返回","索引"，"搜索"对应的ImageButton
     */
    private ImageButton imageButton_back, imageButton_index, imageButton_search;

    /**
     * 声明"我的收藏","地图查询","版权信息","意见反馈"对应的TextView
     */
    private TextView textView_collect, textView_version, textView_ider;

    /**
     * 声明搜索文本框
     */
    private EditText editText_search;

    /**
     * 声明内容片断的集合
     */
    private List<Fragment> contentFragments;

    /**
     * 声明片断管理器对象
     */
    private FragmentManager fragmentManager;

    /**
     * 声明ImageView是否选中的集合
     */
    private List<Boolean> imageViewIsChoseList;

    /**
     * 声明ImageView控件列表的集合
     */
    private List<ImageView> imageViewList;

    /**
     * 声明抽屉是否能打开，默认能够打开，打开后置为false,表示不能打开
     */
    private boolean isDrawerOpen = true;

    /**
     * 声明包管理器
     */
    private PackageManager packageManager;

    /**
     * 声明包信息
     */
    private PackageInfo packageInfo;
    /**
     * 声明之前的时间
     */
    private long preTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_index);

        initView();
    }

    private void initView() {
        textView_headLine = (TextView) findViewById(R.id.textView_headLine);
        textView_headLine.setOnClickListener(this);
        textView_encyclopedia = (TextView) findViewById(R.id.textView_encyclopedia);
        textView_encyclopedia.setOnClickListener(this);
        textView_information = (TextView) findViewById(R.id.textView_information);
        textView_information.setOnClickListener(this);
        textView_operate = (TextView) findViewById(R.id.textView_operate);
        textView_operate.setOnClickListener(this);
        textView_data = (TextView) findViewById(R.id.textView_data);
        textView_data.setOnClickListener(this);
        imageButton_more = (ImageView) findViewById(R.id.imageButton_more);
        imageButton_more.setOnClickListener(this);

        imageView_choose1 = (ImageView) findViewById(R.id.imageView_choose1);
        imageView_choose2 = (ImageView) findViewById(R.id.imageView_choose2);
        imageView_choose3 = (ImageView) findViewById(R.id.imageView_choose3);
        imageView_choose4 = (ImageView) findViewById(R.id.imageView_choose4);
        imageView_choose5 = (ImageView) findViewById(R.id.imageView_choose5);


        //抽屉控件
        drawerLayout_layout = (DrawerLayout) findViewById(R.id.drawerLayout_layout);

        imageButton_back = (ImageButton) findViewById(R.id.imageButton_back);
        imageButton_back.setOnClickListener(this);
        imageButton_index = (ImageButton) findViewById(R.id.imageButton_index);
        imageButton_index.setOnClickListener(this);
        imageButton_search = (ImageButton) findViewById(R.id.imageButton_search);
        imageButton_search.setOnClickListener(this);


        textView_collect = (TextView) findViewById(R.id.textView_collect);
        textView_collect.setOnClickListener(this);
        textView_version = (TextView) findViewById(R.id.textView_version);
        textView_version.setOnClickListener(this);
        textView_ider = (TextView) findViewById(R.id.textView_ider);
        textView_ider.setOnClickListener(this);

        editText_search = (EditText) findViewById(R.id.editText_search);

        //初始化ImageView是否选中的集合，默认为选中第一个
        imageViewIsChoseList = new ArrayList<Boolean>();
        imageViewIsChoseList.add(true);
        imageViewIsChoseList.add(false);
        imageViewIsChoseList.add(false);
        imageViewIsChoseList.add(false);
        imageViewIsChoseList.add(false);

        imageViewList = new ArrayList<ImageView>();
        imageViewList.add(imageView_choose1);
        imageViewList.add(imageView_choose2);
        imageViewList.add(imageView_choose3);
        imageViewList.add(imageView_choose4);
        imageViewList.add(imageView_choose5);

        viewPager_indexPager = (ViewPager) findViewById(R.id.viewPager_indexPager);

        drawerLayout_layout.setDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerStateChanged(int position) {
                Log.e("==drawerListener==", "====onDrawerStateChanged(int position):" + position);

            }

            @Override
            public void onDrawerSlide(View view, float position) {
                Log.e("==drawerListener==", "====onDrawerSlide(View view, float position):" + position);

            }

            @Override
            public void onDrawerOpened(View view) {
                Toast.makeText(getApplicationContext(), "抽屉被打开", Toast.LENGTH_LONG).show();
                isDrawerOpen = false;
                Log.e("==drawerListener==", "====onDrawerOpened(View view)====");

            }

            @Override
            public void onDrawerClosed(View view) {
                Toast.makeText(getApplicationContext(), "抽屉被关闭", Toast.LENGTH_LONG).show();
                isDrawerOpen = true;
                Log.e("==drawerListener==", "====onDrawerClosed(View view)====");

            }
        });

        addFragment();


    }

    private void addFragment() {
        contentFragments = new ArrayList<Fragment>();
        fragmentManager = getSupportFragmentManager();
        viewPager_indexPager.setOffscreenPageLimit(5);
        HeadLineFragment headLineFragment = new HeadLineFragment();
        contentFragments.add(headLineFragment);
        EncyclopediaFragment encyclopediaFragment = new EncyclopediaFragment();
        contentFragments.add(encyclopediaFragment);
        InformationFragment informationFragment = new InformationFragment();
        contentFragments.add(informationFragment);
        OperateFragment operateFragment = new OperateFragment();
        contentFragments.add(operateFragment);
        DataFragment dataFragment = new DataFragment();
        contentFragments.add(dataFragment);


        viewPager_indexPager.setAdapter(new MyAdapter(fragmentManager));

        viewPager_indexPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int i = 0; i < imageViewIsChoseList.size(); i++) {
                    if (imageViewIsChoseList.get(i) == true) {
                        imageViewList.get(i).setBackgroundColor(0xFFFFFFFF);
                        imageViewIsChoseList.set(i, false);
                    }
                }
                imageViewIsChoseList.set(position, true);
                imageViewList.get(position).setBackgroundColor(0xFF009900);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 自定义适配器类
     *
     * @author dell
     */
    private final class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int position) {
            // TODO Auto-generated method stub
            return contentFragments.get(position);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return contentFragments.size();
        }

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        int itemPosition = -1;

        Intent intent = null;
        switch (id) {
            case R.id.imageButton_more:
                //打开抽屉
                this.drawerLayout_layout.openDrawer(Gravity.RIGHT);
                Log.e("==OnClick==", "imageButton_more");
                break;
            case R.id.textView_headLine:
                itemPosition = 0;
                this.setCurrentView(itemPosition);
                break;
            case R.id.textView_encyclopedia:
                itemPosition = 1;
                this.setCurrentView(itemPosition);
                break;
            case R.id.textView_information:
                itemPosition = 2;
                this.setCurrentView(itemPosition);
                break;
            case R.id.textView_operate:
                itemPosition=3;
                this.setCurrentView(itemPosition);
                break;
            case R.id.textView_data:
                itemPosition=4;
                this.setCurrentView(itemPosition);
                break;
            case R.id.imageButton_back:
                //关闭抽屉
                this.drawerLayout_layout.closeDrawer(Gravity.RIGHT);
                break;
            case R.id.imageButton_search:
                //点击搜索跳转
                intent=new Intent(this,SearchResultActivity.class);
                intent.putExtra("search", this.editText_search.getText().toString().trim());
                this.startActivity(intent);
                break;
            case R.id.textView_collect:
                intent=new Intent(this,MyCollectActivity.class);
                this.startActivity(intent);
                break;
            case R.id.textView_version:
                intent = new Intent(this,CopyrightInformationActivity.class);
                this.startActivity(intent);
                break;
            case R.id.textView_ider:
                intent = new Intent(this,AdviceActivity.class);
                this.startActivity(intent);
                break;
            case R.id.imageButton_index:
                //索引抽屉
                break;
            default:
                break;
        }
    }

    public void setCurrentView(int itemPosition) {

        if (this.viewPager_indexPager.getCurrentItem() != itemPosition) {
            this.viewPager_indexPager.setCurrentItem(itemPosition);
            for (int i = 0; i < imageViewIsChoseList.size(); i++) {
                if (imageViewIsChoseList.get(i) == true) {
                    imageViewList.get(i).setBackgroundColor(Color.WHITE);
                    imageViewIsChoseList.set(i, false);
                }
            }

            imageViewIsChoseList.set(itemPosition, true);
            imageViewList.get(itemPosition).setBackgroundColor(Color.BLUE);
        }

    }
}
