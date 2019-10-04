package com.nts.dailyexpense;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class ShowDocActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_doc);

        imageView = findViewById(R.id.imageView);

//        Bitmap image = getIntent().getParcelableExtra("Image");
//        imageView.setImageBitmap(image);
    }
}
