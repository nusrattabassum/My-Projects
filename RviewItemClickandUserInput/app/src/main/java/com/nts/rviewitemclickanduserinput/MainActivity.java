package com.nts.rviewitemclickanduserinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText name, phone, email;
    private Button save;
    private RecyclerView userRV;
    private List<User> userList;
    private CustomAdapter adapter;
    private String aName, aPhone, aEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        initRecyclerView();
        setData();
    }

    private void setData() {

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aName = name.getText().toString();
                aPhone = phone.getText().toString();
                aEmail = email.getText().toString();

                userList.add(new User(aName, aPhone, aEmail));
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecyclerView() {
        userRV.setLayoutManager(new LinearLayoutManager(this));
        userRV.setAdapter(adapter);
    }

    private void init() {

        name = findViewById(R.id.nameET);
        phone = findViewById(R.id.phoneET);
        email = findViewById(R.id.emailET);
        save = findViewById(R.id.saveBTN);
        userRV = findViewById(R.id.userRV);
        userList = new ArrayList<>();
        adapter = new CustomAdapter(userList,this);
    }
}
