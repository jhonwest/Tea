package com.example.tea.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.tea.R;
import com.example.tea.utils.DbUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyCollectActivity extends AppCompatActivity {
    private ListView listView;
    private SimpleAdapter adapter;
    private DbUtils dbUtils;
    private List<Map<String, Object>> data ,data2;
    private Intent intent;
    private String ss = "http://sns.maimaicha.com/news/detail/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        listView = (ListView) findViewById(R.id.listView);
        data = new ArrayList<>();
        data2=new ArrayList<>();

        dbUtils = new DbUtils(this);
        intent = new Intent(this, UrlActivity.class);
        final String name = dbUtils.read();
        if (name != null) {

            System.out.println("MyCollectActivity.name=" + name);

            final List list = Arrays.asList(name);

            System.out.println("MyCollectActivity.list=" + list);

            if (list != null) {
                for (int i = 0; i < list.size(); i++) {
                    String str = list.get(i).toString();
                    Pattern p = Pattern.compile("title=(.*?),");
                    Pattern p1 = Pattern.compile("id=(.*?),");
                    Matcher n = p1.matcher(str);
                    Matcher m = p.matcher(str);
                    while (m.find()) {
                        String title = m.group(1);
                        Map<String, Object> map = new HashMap<>();
                            map.put("title", title);

                            data.add(map);



                    }
                    while (n.find()) {
                        String id = n.group(1);
                        Map<String, Object> map1 = new HashMap<>();
                        map1.put("id",id);
                        data2.add(map1);
                        System.out.println(id);
                    }


                }
            }


            adapter = new SimpleAdapter(this, data, R.layout.collect_item, new String[]{"title"}, new int[]{R.id.textView});
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Map<String,Object> bb = data2.get(position);
                    String name1= (String) bb.get("bb");
                    String sbb=ss+name1;
                    System.out.println("ssb="+sbb);
                    intent.putExtra("url",sbb);
                    startActivity(intent);
                }
            });
        }
    }

}





