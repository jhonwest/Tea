package com.example.tea.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.tea.Activity.IndexActivity;
import com.example.tea.R;

/**
 * Created by lenovo on 2016/10/8.
 */
public class LoadPager3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_loadpage3,container,false);
        Button button_toIndex = (Button) view.findViewById(R.id.button_toIndex);
        button_toIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IndexActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
