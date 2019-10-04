package com.nts.rviewitemclickanduserinput;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView name,phone,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.detailsNameTV);
        phone = findViewById(R.id.detailsPhoneTV);
        email = findViewById(R.id.detailsEmailTV);

        String aName = getIntent().getStringExtra("Name:");
        String aPhone = getIntent().getStringExtra("Phone:");
        String aEmail = getIntent().getStringExtra("Email:");
        name.setText("Name: "+aName);
        phone.setText("Phone No: "+aPhone);
        email.setText("Email: "+aEmail);
    }
}
