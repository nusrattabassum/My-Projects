package com.nts.sqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText name, age;
    private Button insert, show;
    private DatabaseHelper databaseHelper;
    private String aName,aAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                aName = name.getText().toString();
                aAge = age.getText().toString();

                long id = databaseHelper.insertData(aName, aAge);

                Toast.makeText(MainActivity.this,"Data Serial: "+id,Toast.LENGTH_SHORT).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void init() {

        name = findViewById(R.id.nameET);
        age = findViewById(R.id.ageET);
        insert = findViewById(R.id.insertBTN);
        show = findViewById(R.id.showDetailsBTN);
        databaseHelper = new DatabaseHelper(this);
    }
}
