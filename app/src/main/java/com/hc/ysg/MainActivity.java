package com.hc.ysg;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.hc.ysg.fragment.MeFragment;
import com.hc.ysg.fragment.PengYouFragment;
import com.hc.ysg.fragment.QuanFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主页
 * Created by yushuaiguang on 2017/8/25.
 */
public class MainActivity extends AppCompatActivity {
    private List<Fragment> fragmentList = new ArrayList<>();
    private CustomTitleBar titleLayout;
    private ViewPager viewPager;
    private RadioGroup radioGroup;
    private MainActivity.fragmentAdapter fAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//初始化数据
    }

    private void initView() {
        titleLayout = (CustomTitleBar) findViewById(R.id.titlelayout);
        viewPager = (ViewPager) findViewById(R.id.vp);
        radioGroup = (RadioGroup) findViewById(R.id.rg);
        fragmentData();
        fAdapter = new fragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fAdapter);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.quanzi:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.pengyou:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.me:
                        viewPager.setCurrentItem(2);
                        break;
                }
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        radioGroup.check(R.id.quanzi);
                        break;
                    case 1:
                        radioGroup.check(R.id.pengyou);
                        break;
                    case 2:
                        radioGroup.check(R.id.me);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    //fragment添加到集合中
    private void fragmentData() {
        QuanFragment quanFragment = new QuanFragment();
        PengYouFragment pyFragmeng = new PengYouFragment();
        MeFragment meFragment = new MeFragment();
        fragmentList.add(quanFragment);
        fragmentList.add(pyFragmeng);
        fragmentList.add(meFragment);
    }

    class fragmentAdapter extends FragmentPagerAdapter {

        public fragmentAdapter(FragmentManager fm) {
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
