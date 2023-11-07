package com.example.chanakyaniti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;


import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private ViewPager view_pager;
    private TabLayout tab_layout;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=this;
        view_pager=findViewById(R.id.view_pager);
        tab_layout=findViewById(R.id.tab_layout);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("चाणक्य नीति");
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        List<Fragment> mFragmentList = new ArrayList<>();
        List<String> mUrl=new ArrayList<>();
        for(int i=1;i<=17;i++)
        {
            mUrl.add("file:///android_asset/Chapter_"+i+".html");
            tab_layout.addTab(tab_layout.newTab().setText("अध्याय"+" "+i));
        }
        for(int i=0; i <17; i++){
            ChanakyaNitiFragement fragment = ChanakyaNitiFragement.newInstance(mUrl.get(i));
            mFragmentList.add(fragment);

        }

        FragmentAdapter fragmentAdapter=new FragmentAdapter(getSupportFragmentManager(),mFragmentList);
        tab_layout.setTabGravity(tab_layout.GRAVITY_FILL);
        tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);
        view_pager.setAdapter(fragmentAdapter);
        view_pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));
        tab_layout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                view_pager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                Log.i("TAG", "onNavigationItemSelected: ");
                if (id == R.id.nav_home)
                {

                }
                else if (id == R.id.nav_more_application)
                {
                    Intent intent=new Intent(MainActivity.this,MoreAppActivity.class);
                    startActivity(intent);
//
                } else if (id == R.id.nav_rating)
                {
                        openApp("com.chanakya.niti.hindi.full","https://play.google.com/store/apps/details?id=com.chanakya.niti.hindi.full");
                } else if (id == R.id.nav_share)
                {
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.chanakya.niti.hindi.full");
                    shareIntent.setType("text/plain");
                    startActivity(Intent.createChooser(shareIntent,"share via"));
                } else if (id == R.id.nav_privacy)
                {
                    Intent intent=new Intent(MainActivity.this,PrivacyPolicyActivity.class);
                    startActivity(intent);
                }
                else if (id == R.id.nav_exit)
                {
                    finish();
                }
                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen( GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    private void openApp(String appid, String applink)
    {
        Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=" +appid);
        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        goToMarket.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_NEW_DOCUMENT | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
        try
        {
            startActivity(goToMarket);

        } catch (ActivityNotFoundException e)
        {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(applink)));
        }
    }


}