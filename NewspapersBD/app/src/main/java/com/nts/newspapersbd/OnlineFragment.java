package com.nts.newspapersbd;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class OnlineFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<News> newsItems;
    private OnlineNewsAdapter adapter;

    public OnlineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_online, container, false);
        init(view);
        setData(view);

        return view;
    }

    private void setData(View view) {
        newsItems.add(new News(R.drawable.logo_bdnews,"https://bdnews24.com/"));
        newsItems.add(new News(R.drawable.logo_banglanews24,"https://www.banglanews24.com/"));
        newsItems.add(new News(R.drawable.logo_bbc_bangla,"https://www.bbc.com/bengali"));
        newsItems.add(new News(R.drawable.logo_bangla_tribune,"http://www.banglatribune.com/"));
        newsItems.add(new News(R.drawable.logo_bd24_live,"https://www.bd24live.com/"));
        newsItems.add(new News(R.drawable.logo_jagonews24,"https://www.jagonews24.com/"));
        newsItems.add(new News(R.drawable.logo_barta24,"https://barta24.com/"));
        newsItems.add(new News(R.drawable.logo_poriborton,"https://www.poriborton.com/"));
        newsItems.add(new News(R.drawable.logo_bartabangla,"https://bartabangla.com/"));
        newsItems.add(new News(R.drawable.logo_thereport24,"http://bangla.thereport24.com/"));
        newsItems.add(new News(R.drawable.logo_rtnn,"http://m.bdrtnn.net/"));
        newsItems.add(new News(R.drawable.logo_shomoyer_kontho,"http://www.somoyerkonthosor.com/"));
        newsItems.add(new News(R.drawable.logo_risingbd,"https://risingbd.com/"));
        newsItems.add(new News(R.drawable.logo_deshnews,"http://deshnews24.com/"));
        newsItems.add(new News(R.drawable.logo_dhaka_times,"https://thedhakatimes.com/"));
        newsItems.add(new News(R.drawable.logo_crime_express,"http://www.crimeexpressbd.com/"));

        adapter.notifyDataSetChanged();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        newsItems = new ArrayList<>();
        adapter = new OnlineNewsAdapter(newsItems,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
    }

}
