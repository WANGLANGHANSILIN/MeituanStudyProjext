package com.wanglang.bottomnavigation.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import com.wanglang.bottomnavigation.R;
import com.wanglang.bottomnavigation.fragments.AroundFragment;
import com.wanglang.bottomnavigation.fragments.HomeFragment;
import com.wanglang.bottomnavigation.fragments.MineFragment;
import com.wanglang.bottomnavigation.fragments.MoreFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FragmentTabHost viewTabHost;



    private int[] imgResIds = new int[]{R.drawable.ic_draw_tab_home,R.drawable.ic_draw_tab_around,R.drawable.ic_draw_tab_mine,
    R.drawable.ic_draw_tab_more};
    private String[] mStrings = new String[]{"首页","附近","我的","更多"};
    private Fragment[] mFragments = {new HomeFragment(),new AroundFragment(),new MineFragment(),new MoreFragment()};
    private ViewPager mainVp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("MainActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initTabView();
    }

    //初始化底部导航栏
    private void initTabView() {
        viewTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mainVp = (ViewPager) findViewById(R.id.vp_main);
        viewTabHost.setup(this,getSupportFragmentManager(),R.id.vp_main);


        viewTabHost.getTabWidget().setMinimumHeight(120);
        viewTabHost.getTabWidget().setDividerDrawable(null);

        for (int i = 0; i < mStrings.length; i++) {
            View inflate = getLayoutInflater().inflate(R.layout.bouttom_tab_item,null);
            TextView tvTab = (TextView) inflate.findViewById(R.id.tv_tab);
            ImageView ivTab = (ImageView) inflate.findViewById(R.id.iv_tab);
            tvTab.setText(mStrings[i]);
            ivTab.setImageResource(imgResIds[i]);
            viewTabHost.addTab(viewTabHost.newTabSpec(""+i).setIndicator(inflate),mFragments[i].getClass(),null);
        }
        viewTabHost.setCurrentTab(0);
        viewTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                mainVp.setCurrentItem(Integer.valueOf(tabId));
            }
        });
        mainVp.setAdapter(new MyFrAdapter(getSupportFragmentManager()));
        mainVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                viewTabHost.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class MyFrAdapter extends FragmentPagerAdapter{

        public MyFrAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments[position];
        }

        @Override
        public int getCount() {
            return mFragments.length;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFragments[0].onActivityResult(requestCode,resultCode,data);
    }
}
