package com.example.tea.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tea.R;

/**
 * Created by lenovo on 2016/10/12.
 */
public class HeadSecendFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.two_fragment,container,false);
        return view;
    }
}
