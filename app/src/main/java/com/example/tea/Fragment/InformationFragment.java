package com.example.tea.Fragment;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import com.example.tea.utils.HttpUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by lenovo on 2016/10/13.
 */
public class InformationFragment extends Fragment {

    private ListView listView;
    private List<String> listUrl;
    private String str,title;
    private StringBuffer sb1,sb2;
    private SimpleAdapter simpleAdapter;
    private List<Map<String,Object>> datas = new ArrayList<>();

    String path = "http://sns.maimaicha.com/api?apikey=b4f4ee31a8b9acc866ef2afb754c33e6&format=json&method=news.getListByType&page=0&rows=15&type=52";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_encyclopedia,container,false);
        listView = (ListView) view.findViewById(R.id.listView);
        new Test().execute(path);
        return view;
    }

    public final class Test extends AsyncTask<String, Void, List<Map<String, Object>>> {

        @Override
        protected List<Map<String, Object>> doInBackground(String... params) {
            InputStream inputStream = HttpUtils.getInputSteam(params[0]);

            if (inputStream != null) {
                String json = HttpUtils.getString(inputStream);

                if (json != null) {
                    String str = CharSetUtil.decodeUnicode(json);
                    List<Map<String, Object>> mapList = HttpUtils.getDrugStore(str);

                    return mapList;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(final List<Map<String, Object>> maps) {
            super.onPostExecute(maps);
            final String mapStr = maps.toString();
            listUrl=new ArrayList<>();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Map<String ,Object> strMap = maps.get(position - 1);
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
                    intent.putExtra("id",str);
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

}
