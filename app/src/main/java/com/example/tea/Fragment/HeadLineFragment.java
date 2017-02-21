package com.example.tea.Fragment;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.tea.Activity.UrlActivity;
import com.example.tea.R;
import com.example.tea.utils.CharSetUtil;
import com.example.tea.utils.DBManager;
import com.example.tea.utils.HttpUtils;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by lenovo on 2016/10/8.
 */
public class HeadLineFragment extends Fragment {

    /**
     * 声明网址的集合
     */
    private List<String> listUrl;

    private String str,title;

    private StringBuffer sb1,sb2;
    /**
     * 声明数据的集合
     */
    private List<Map<String,Object>> datas = new ArrayList<>();

    /**
     * 声明数据库管理器对象
     */
    private DBManager dbManager;
    private JSONObject jsonObject;

    /**
     * 声明Fragment集合
     */

    private List<Fragment> fragmentList;


    /**
     * 声明加载管理器对象
     */
    private LoaderManager loaderManager;

    /**
     * 声明碎片管理器对象
     */
    private FragmentManager fragmentManager;

    /**
     * 声明ListView控件
     */
    private ListView listView;

    /**
     * 声明头部Item(包含ViewPager)
     */
    private View view_head;

    /**
     * 声明头部Item里的ViewPaget
     * */
    private ViewPager viewPager;


    /**
     * 声明适配器对象
     */
    private static MyAdapter myAdapter;

    /**
     * 声明ListView的适配器对象
     */
    private SimpleAdapter simpleAdapter;

    /**
     * 声明游标对象
     */
    private Cursor cursor;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_headline,container,false);
        listView = (ListView) view.findViewById(R.id.listView);
        view_head = LayoutInflater.from(getContext()).inflate(R.layout.headitem,null);
        viewPager = (ViewPager) view_head.findViewById(R.id.viewpager);


        fragmentManager = getChildFragmentManager();
        fragmentList = new ArrayList<>();
        fragmentList.add(new HeadFirstFragment());
        fragmentList.add(new HeadSecendFragment());
        fragmentList.add(new HeadThirdFragment());

        //添加头部item
        listView.addHeaderView(view_head);

        myAdapter = new MyAdapter(fragmentManager);
        viewPager.setAdapter(myAdapter);
        viewPager.setOffscreenPageLimit(fragmentList.size());

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        new Test().execute("http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getHeadlines");
        return view;
    }


    /**
     * 给ListView添加数据源
     */
    public final class Test extends AsyncTask<String, Void, List<Map<String, Object>>> {

        @Override
        protected List<Map<String, Object>> doInBackground(String... params) {
            InputStream inputStream = HttpUtils.getInputSteam(params[0]);

            if (inputStream != null) {
                String json = getString(R.string.json_qiyoulianmeng);

                if (json != null) {
                    String str = CharSetUtil.decodeUnicode(json);
                    Log.e("=====", "====AsyncTask.str=" + str);
                    List<Map<String, Object>> mapList = HttpUtils.getDrugStore(str);
                    Log.e("=====", "====AsyncTask.maplist=" + mapList);

                    return mapList;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<Map<String, Object>> maps) {
            super.onPostExecute(maps);
            final String mapStr = maps.toString();
            listUrl = new ArrayList<>();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Map<String ,Object> strMap = maps.get(position - 1);
                    str = (String) strMap.get("author");
                    title = (String) strMap.get("title");

                    String url1 = "http://sns.maimaicha.com/news/detail/";
                    sb1 = new StringBuffer();
                    sb1.append(url1).append(str);
                    sb2 = new StringBuffer();
                    sb2.append(strMap.toString());

                    listUrl.add(strMap.toString());
                    String sbStr = sb1.toString();
                    Intent intent = new Intent(getActivity(),UrlActivity.class);
                    intent.putExtra("url",sbStr);
                    intent.putExtra("title",title);
                    intent.putExtra("author",str);
                    intent.putExtra("collect",sb2.toString());
                    startActivity(intent);

                }
            });
            if((maps != null) && (maps.size() != 0 )){
                datas.addAll(maps);
                if(simpleAdapter == null){
                    simpleAdapter = new SimpleAdapter(getActivity(),datas,R.layout.item,new String[]{"wapthumb", "title", "source", "nickname", "createtime"},
                            new int[]{R.id.imageView,R.id.textView_title,R.id.textView_class,R.id.textView_url,R.id.textView_time});
                    listView.setAdapter(simpleAdapter);

                    simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder() {
                        @Override
                        public boolean setViewValue(View view, Object data, String textRepresentation) {
                            if (view instanceof ImageView){
                                final ImageView wapthumb = (ImageView) view;

                                class LodaImageAsyncTask extends AsyncTask<String , Void , Bitmap>{

                                    @Override
                                    protected Bitmap doInBackground(String... params) {
                                        InputStream inputStream = HttpUtils.getInputSteam(params[0]);

                                        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                                        return bitmap;
                                    }

                                    @Override
                                    protected void onPostExecute(Bitmap bitmap) {
                                        if(bitmap != null){
                                            wapthumb.setImageBitmap(bitmap);
                                        }
                                    }
                                }
                                new LodaImageAsyncTask().execute(textRepresentation);
                                return true;
                            }
                            return false;
                        }
                    });
                }else{
                    simpleAdapter.notifyDataSetChanged();
                }
            }

        }
    }

    private class MyAdapter extends FragmentStatePagerAdapter{


        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }
    }


}
