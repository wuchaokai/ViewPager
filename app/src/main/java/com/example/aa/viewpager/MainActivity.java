package com.example.aa.viewpager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Window;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Bitmap bitmap;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        tabLayout=(TabLayout)findViewById(R.id.tittle);
        bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.pm2);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        initViewPager();
        initTab();
        changeColor();
    }

    private void changeColor() {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrant=palette.getVibrantSwatch();
                toolbar.setBackgroundColor(vibrant.getRgb());
                Window window=getWindow();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    window.setStatusBarColor(vibrant.getRgb());
                }
            }
        });
    }

    private void initTab() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tabLayout.getSelectedTabPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initViewPager() {
        List<Fragment> fragments=new ArrayList<>();
        fragments.add(new leftFragment());
        fragments.add(new centerFragment());
        fragments.add(new rightFragment());
        ViewPageAdapter adapter=new ViewPageAdapter(getSupportFragmentManager(),fragments);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
                switch (position){
                    case 0:bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.pm2);
                        changeColor();
                        break;
                    case 1:bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.pm1);
                        changeColor();
                        break;
                    case 2:bitmap=BitmapFactory.decodeResource(getResources(),R.drawable.pm3);
                        changeColor();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
