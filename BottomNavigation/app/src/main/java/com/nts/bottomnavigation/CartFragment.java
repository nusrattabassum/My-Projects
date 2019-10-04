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

public class CartFragment extends Fragment {

    private List<Details> detailsList;
    private RecyclerView cartRV;
    private  CartAdapter adapter;

    public CartFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        init(view);
        initRecyclerView();
        getData();

        return view;
    }

    private void getData() {

        detailsList.add(new Details(R.drawable.frock,"Frock","Quantity: 10","Price: 800.00"));
        detailsList.add(new Details(R.drawable.shoes,"Shoes","Quantity: 5", "Price: 1500.00"));
        detailsList.add(new Details(R.drawable.shirt,"Shirt","Quantity: 10", "Price: 1600.00"));
        detailsList.add(new Details(R.drawable.tshirt,"Tshirt","Quantity: 7", "Price: 500.00"));
        detailsList.add(new Details(R.drawable.gents_pant,"Pant","Quantity: 3", "Price: 1800.00"));
    }

    private void initRecyclerView() {

        cartRV.setLayoutManager(new LinearLayoutManager(getContext()));
        cartRV.setAdapter(adapter);
    }

    private void init(View view) {

        cartRV = view.findViewById(R.id.cartRV);
        detailsList = new ArrayList<>();
        adapter = new CartAdapter(detailsList);
    }

}
