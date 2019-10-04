package com.nts.newspapersbd;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class BanglaFragment extends Fragment {

    private RecyclerView recyclerView;
    private List<News> newsItems;
    private BanglaNewsAdapter adapter;

    private DrawerLayout drawer;
    private Toolbar toolbar;

    public BanglaFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bangla, container, false);
        init(view);
        setData(view);

        return view;
    }

    private void setData(View view) {

        newsItems.add(new News(R.drawable.logo_prothom_alo,"https://www.prothomalo.com/"));
        newsItems.add(new News(R.drawable.logo_noya_digonto,"https://www.dailynayadiganta.com"));
        newsItems.add(new News(R.drawable.logo_jugantor,"https://www.jugantor.com/"));
        newsItems.add(new News(R.drawable.logo_kaler_kontho,"https://www.kalerkantho.com/"));
        newsItems.add(new News(R.drawable.daily_ittefaq,"https://www.ittefaq.com.bd/"));
        newsItems.add(new News(R.drawable.logo_shamakal,"https://samakal.com/"));
        newsItems.add(new News(R.drawable.logo_amar_desh,"https://amar-desh24.com/bangla/"));
        newsItems.add(new News(R.drawable.logo_janakantha,"http://www.dailyjanakantha.com"));
        newsItems.add(new News(R.drawable.logo_manabjomin,"https://www.mzamin.com/"));
        newsItems.add(new News(R.drawable.logo_inquilab,"https://www.dailyinqilab.com/"));
        newsItems.add(new News(R.drawable.logo_amader_shomoy,"http://www.dainikamadershomoy.com/"));
        newsItems.add(new News(R.drawable.logo_destiny,"https://dainik-destiny.com/"));
        newsItems.add(new News(R.drawable.logo_dinkal,"https://www.dailydinkal.net/2019/09/10/index.php"));
        newsItems.add(new News(R.drawable.logo_vorer_pata,"http://dailyvorerpata.com/"));

        adapter.notifyDataSetChanged();
    }

    private void init(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        newsItems = new ArrayList<>();
        adapter = new BanglaNewsAdapter(newsItems,getContext());
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setAdapter(adapter);

        drawer = view.findViewById(R.id.drawerLayout);
        toolbar = view.findViewById(R.id.toolBar);
    }
}
