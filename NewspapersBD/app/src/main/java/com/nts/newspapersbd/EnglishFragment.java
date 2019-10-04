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

public class EnglishFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<News> newsItems;
    private EnglishNewsAdapter adapter;

    public EnglishFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_english, container, false);
        init(view);
        setData(view);

        return view;
    }

    private void setData(View view) {
        newsItems.add(new News(R.drawable.logo_daily_star,"https://www.thedailystar.net"));
        newsItems.add(new News(R.drawable.logo_independent,"http://www.theindependentbd.com/"));
        newsItems.add(new News(R.drawable.logo_dhaka_tribune,"https://www.dhakatribune.com/"));
        newsItems.add(new News(R.drawable.logo_new_age,"http://www.newagebd.net/"));
        newsItems.add(new News(R.drawable.logo_daily_sun,"https://www.daily-sun.com/"));
        newsItems.add(new News(R.drawable.logo_financial_express,"https://thefinancialexpress.com.bd/"));
        newsItems.add(new News(R.drawable.logo_observer,"https://www.observerbd.com/"));
        newsItems.add(new News(R.drawable.logo_bd_today,"https://thebangladeshtoday.com/"));
        newsItems.add(new News(R.drawable.logo_new_nation,"http://thedailynewnation.com/"));
        newsItems.add(new News(R.drawable.logo_newstoday,"http://www.newstoday.com.bd/"));
        newsItems.add(new News(R.drawable.logo_the_guardian,"https://www.theguardian.com/international"));
        newsItems.add(new News(R.drawable.logo_daily_asian_age,"https://www.asianage.com/"));

        adapter.notifyDataSetChanged();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        newsItems = new ArrayList<>();
        adapter = new EnglishNewsAdapter(newsItems,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);
    }

}
