package com.hc.ysg.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hc.ysg.R;
import com.hc.ysg.adapter.RecyclerAdapter;
import com.hc.ysg.bean.Bean;
import com.hc.ysg.utils.OkHttpUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 圈子Fragment
 * Created by YU on 2017/8/25.
 */

public class QuanFragment extends Fragment {
    private String url = "http://139.196.140.118:8080/get/%7B%22%5B%5D%22:%7B%22page%22:0,%22count%22:10,%22Moment%22:%7B%22content$%22:%22%2525a%2525%22%7D,%22User%22:%7B%22id@%22:%22%252FMoment%252FuserId%22,%22@column%22:%22id,name,head%22%7D,%22Comment%5B%5D%22:%7B%22count%22:2,%22Comment%22:%7B%22momentId@%22:%22%5B%5D%252FMoment%252Fid%22%7D%7D%7D%7D";
    private View view;
//    private int page = 1;
    private RecyclerView mRecy;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Bean.ysgBean> list = new ArrayList<>();
    private RecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.quanzifragment, null);
        initView();
        startTask();
        return view;
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe);
        mRecy = (RecyclerView) view.findViewById(R.id.recyclerview);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        mRecy.setLayoutManager(linearLayoutManager);
        //上拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                startTask();
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        //下拉加载
        mRecy.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == list.size() - 1) {
//                    page++;
                    startTask();
                    adapter.notifyDataSetChanged();
                    ;
                }
            }
        });
    }
    //网络请求
    private void startTask() {

        //通过类名直接调用静态方法获取单例对象再调用getBeanOfOK()方法传入参数通过接口回调获取数据
        OkHttpUtils.getInstance().getBeanOfOk(getActivity(), url , Bean.class,
                new OkHttpUtils.CallBack<Bean>() {
                    @Override
                    public void getData(Bean testBean) {

                        Log.i("===", "getData: " + testBean.toString());
                        if (testBean != null) {
                            //如果不为空用本地list接收
                            list=testBean.ysg;
                            initAdapter();
                        }
                    }
                });
    }
    //设置adapter
    public void initAdapter() {
        adapter = new RecyclerAdapter(getActivity(), list);
        mRecy.setAdapter(adapter);
    }
}