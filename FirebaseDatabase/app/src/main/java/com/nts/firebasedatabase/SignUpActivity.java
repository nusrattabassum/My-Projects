package com.nts.firebasedatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.nts.firebasedatabase.databinding.ActivitySignInBinding;
import com.nts.firebasedatabase.databinding.ActivitySignUpBinding;

import java.util.HashMap;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_sign_up);

        init();

        binding.signInTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUpActivity.this,SignInActivity.class));
                finish();
            }
        });

        binding.signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = binding.nameET.getText().toString();
                String email = binding.emailET.getText().toString();
                String password = binding.passwordET.getText().toString();
                String cpassword = binding.confirmPasswordET.getText().toString();

                if (password.equals(cpassword)){
                    signUp(name,email,password);
                }
                else {
                    Toast.makeText(SignUpActivity.this,"Password doesn't match",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    private void signUp(final String name, final String email, String password) {

        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    String userId = firebaseAuth.getCurrentUser().getUid();
                    DatabaseReference userRef = databaseReference.child("users").child(userId);

                    HashMap<String,Object> userMap = new HashMap<>();
                    userMap.put("name",name);
                    userMap.put("email",email);

                    userRef.setValue(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){

                                startActivity(new Intent(SignUpActivity.this,MainActivity.class));
                            }
                        }
                    });

                }else{
                    Toast.makeText(SignUpActivity.this, "SignUp failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
