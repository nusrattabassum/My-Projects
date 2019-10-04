package com.nts.firebaseauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPageActivity extends AppCompatActivity {

    private EditText phoneNoET;
    private Button login;
    private String phoneNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        phoneNoET = findViewById(R.id.phoneNoET);
        login = findViewById(R.id.loginBTN);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                phoneNo = phoneNoET.getText().toString();
                if (phoneNo.length() == 11){

                    Intent intent = new Intent(LoginPageActivity.this,VerifyActivity.class);
                    intent.putExtra("phone",phoneNo);
                    startActivity(intent);
                }

                else {
                    Toast.makeText(LoginPageActivity.this,"Invalid input",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
