package com.example.tea.Activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tea.R;

/**
 * Created by lenovo on 2016/10/14.
 */
public class AdviceActivity extends AppCompatActivity {
    private EditText et1,et2,et3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AdviceActivity.this,"您的意见已提交",Toast.LENGTH_LONG).show();
                et1 = (EditText) findViewById(R.id.et1);
                et1.setText("");
                et2 = (EditText) findViewById(R.id.et2);
                et2.setText("");
                et3 = (EditText) findViewById(R.id.et3);
                et3.setText("");

            }
        });
    }
}
