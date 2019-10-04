package com.nts.imagestoragefirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    
    private ImageView imageView;
    private Button selectBtn,uploadBtn;
    private Uri uri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private String downloadLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        imageView = findViewById(R.id.imageView);
        selectBtn = findViewById(R.id.selectBTN);
        uploadBtn = findViewById(R.id.uploadBTN);
        
        selectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                openGallery(view);
            }
        });

        uploadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uploadImage(view);
            }
        });
    }


    private void openGallery(View view) {
        Intent intent= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent,1);
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK){
                if(requestCode == 1 && data != null && data.getData() != null){
                uri = data.getData();
                imageView.setImageURI(uri);
            }
        }
    }

    public void uploadImage(View view) {

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        final StorageReference ref = storageReference.child("Image"+ UUID.randomUUID());
        ref.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        downloadLink = uri.toString();
                        
                        uploadDownloadLink(downloadLink);
                    }
                });
            }
        });
    }

    private void uploadDownloadLink(String downloadLink) {
    }
}
