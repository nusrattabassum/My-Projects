package com.nts.r_viewitem2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private TextView name,phone,email;
    private ImageView imaage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        name = findViewById(R.id.detailsName);
        phone = findViewById(R.id.detailsPhone);
        email = findViewById(R.id.detailsEmail);
        imaage = (ImageView)findViewById(R.id.image2);

        String aName = getIntent().getStringExtra("Name");
        String aPhone = getIntent().getStringExtra("Phone");
        String aEmail = getIntent().getStringExtra("Email");

        Bundle bundle = getIntent().getExtras();
        int img = bundle.getInt("image");
        imaage.setImageResource(img);

        name.setText("Name: "+aName);
        phone.setText("Phone: "+aPhone);
        email.setText("Email: "+aEmail);
    }
}
