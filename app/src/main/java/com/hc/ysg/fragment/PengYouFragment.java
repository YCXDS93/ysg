package com.hc.ysg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hc.ysg.R;

/**
 * 朋友Fragment
 * Created by YU on 2017/8/25.
 */

public class PengYouFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.pengyoufragment,null);
        return view;
    }
}
