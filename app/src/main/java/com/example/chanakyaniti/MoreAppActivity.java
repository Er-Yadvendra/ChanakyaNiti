package com.example.chanakyaniti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MoreAppActivity extends AppCompatActivity {
    Context mContext;
    private RecyclerView reclr_more_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_app);
        mContext=this;
        reclr_more_app=findViewById(R.id.reclr_more_app);


        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reclr_more_app.setLayoutManager(new LinearLayoutManager(mContext));
        ArrayList<MoreAppPojo> list=new ArrayList<>();
        list.add(new MoreAppPojo(R.drawable.app0,getResources().getString(R.string.app0),getResources().getString(R.string.link0),getResources().getString(R.string.id0), 4.5f));
        list.add(new MoreAppPojo(R.drawable.app2,getResources().getString(R.string.app2),getResources().getString(R.string.link2),getResources().getString(R.string.id2),4));
        list.add(new MoreAppPojo(R.drawable.app3,getResources().getString(R.string.app3),getResources().getString(R.string.link3),getResources().getString(R.string.id3),5));


        // add 3 parameter
        // 1. Full link
        // 2. only id
        // 3. rating

        MoreAppAdapter adapter=new MoreAppAdapter(mContext, list, new RecyclerViewOnItemClick()
        {
            @Override
            public void onItemClick(View view, int position)

            {
                switch(view.getId())
                {
                    case R.id.card_layout:
                    case R.id.btn_signin:
                        openApp(list.get(position).getOnly_id(),list.get(position).getFull_link());
                        break;
                }

                Log.i("TAG", "onItemClick: "+list.get(position).getFull_link());
                    Toast.makeText(mContext, "view clicked", Toast.LENGTH_SHORT).show();
            }
        });
        reclr_more_app.setAdapter(adapter);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(mContext,2);
//        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(mContext);
        reclr_more_app.setLayoutManager(gridLayoutManager);
        reclr_more_app.setItemAnimator(new DefaultItemAnimator());
    }

// ho gya?
    // doosr
    // wo bhi ho gya

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


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);

    }
}