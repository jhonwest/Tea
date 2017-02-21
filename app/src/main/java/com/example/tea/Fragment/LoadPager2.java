package com.example.tea.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tea.R;

/**
 * 声明加载的第二页
 * @author dell
 *
 */
public class LoadPager2 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_loadpage2, container,false);
        return view;
    }
}
