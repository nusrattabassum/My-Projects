package com.nts.bottomnavigation;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HomeFragment extends Fragment {

    private List<Product> products;
    private HomeAdapter adapter;
    private RecyclerView homeRV;


    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        initRecyclerView();
        getData();
        return view;
    }

    private void getData() {

        products.add(new Product("Frock",R.drawable.frock));
        products.add(new Product("Shoes",R.drawable.shoes));
        products.add(new Product("Shirt",R.drawable.shirt));
        products.add(new Product("Tshirt",R.drawable.tshirt));
        products.add(new Product("Pant",R.drawable.gents_pant));

        adapter.notifyDataSetChanged();
    }

    private void initRecyclerView() {

        homeRV.setLayoutManager(new LinearLayoutManager(getContext()));
        homeRV.setAdapter(adapter);
    }

    private void init(View view) {

        products = new ArrayList<>();
        homeRV = view.findViewById(R.id.homeRV);
        adapter = new HomeAdapter(products);

    }

}
