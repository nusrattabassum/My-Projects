package com.nts.sqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private List<User> userList;
    private RecyclerView recyclerView;
    private UserAdapter adapter;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        init();
        initRecyclerView();
        setData();
    }

    private void setData() {

        Cursor cursor = helper.showData();

        while (cursor.moveToNext()){

            int id = cursor.getInt(cursor.getColumnIndex(helper.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(helper.COL_NAME));
            String age = cursor.getString(cursor.getColumnIndex(helper.COL_AGE));

            userList.add(new User(id, name, age));
            adapter.notifyDataSetChanged();
        }
    }

    private void initRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void init() {

        userList = new ArrayList<>();
        recyclerView = findViewById(R.id.detailsRV);
        adapter = new UserAdapter(userList,this, helper);
        helper = new DatabaseHelper(this);
    }
}
