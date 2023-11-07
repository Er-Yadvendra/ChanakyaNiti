package com.example.chanakyaniti;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

public class ChanakyaNitiFragement extends Fragment {

WebView web_View;
String string;
    public ChanakyaNitiFragement(String string) {
        this.string=string;
        // Required empty public constructor
    }

    public static ChanakyaNitiFragement newInstance(String string) {
        ChanakyaNitiFragement fragment = new ChanakyaNitiFragement(string);

        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_chankya_niti_fragement, container, false);
        web_View= (WebView)view.findViewById(R.id.web_View);
        WebSettings webSettings = web_View.getSettings();
        webSettings.setJavaScriptEnabled(true);
        web_View.loadUrl(string);
        return  view;
    }
}