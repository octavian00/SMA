package com.example.tema1.pictures;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tema1.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

public class PhotoGalery extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE=1543;
    private PictureAdapter mPictureAdapter;
    private Button upload, download;
    private RecyclerView uploadList;
    private ArrayList<Uri> imagesUri;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private EditText edt_UrlPicture;
    private static final String path="/storage/emulated/0/Download/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_galery);
        initializeViews();
        setOnClickListeners();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        Log.e("onActivityResult","befor IF");
        if(requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK &&
                data!=null && data.getClipData() != null ){
            Log.e("onActivityResult","execute IF");
            imagesUri.clear();
            ClipData clipData = data.getClipData();
            for(int i = 0 ; i< clipData.getItemCount(); i++){
                Uri mUri = clipData.getItemAt(i).getUri();
                Log.e("onActivityResult",data.getData()+"");
                imagesUri.add(mUri);
                uploadPicture(mUri);
            }

            setRecyclerView();
        }

    }

    private void setOnClickListeners() {
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select photos"),RESULT_LOAD_IMAGE);
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadPicture();
            }
        });
    }

    private void setRecyclerView(){
        if(imagesUri != null){
            mPictureAdapter = new PictureAdapter(imagesUri);
        }
        Log.e("PhotoGalery",imagesUri.size()+"");
        uploadList.setLayoutManager(new LinearLayoutManager(this));
        uploadList.setHasFixedSize(true);
        uploadList.setAdapter(mPictureAdapter);
    }

    private void initializeViews(){
        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        imagesUri = new ArrayList<>();
        upload = findViewById(R.id.btn_upload_photo);
        uploadList = findViewById(R.id.rv_upload_list);
        edt_UrlPicture = findViewById(R.id.edt_uriDownload);
        download = findViewById(R.id.btn_downloadStorage);
    }
    private void uploadPicture(Uri imageUri){
        if(imageUri == null){
            Log.e("imageUri ", "=NULL");
            return;
        }
        final String randomKey = UUID.randomUUID().toString();
        StorageReference riversRef = storageReference.child("images/"+randomKey);

        riversRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"Upload Succes",Toast.LENGTH_LONG).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(),"Failed to Upload",Toast.LENGTH_LONG).show();
                    }
                });
    }

    private void downloadPicture(){
        String pictureUrl = edt_UrlPicture.getText().toString();
        Log.e("PICTURE Url", pictureUrl+"");
        if(pictureUrl == ""){
            Toast.makeText(getApplicationContext(),"Please fill with  URI",Toast.LENGTH_LONG).show();
            return;
        }
        StorageReference refForDownload = firebaseStorage.getReferenceFromUrl("gs://upt-sma.appspot.com/images");
        StorageReference riversRef=refForDownload.child(pictureUrl);
        File localFile = new File(path, pictureUrl);
        riversRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(getApplicationContext(),"Succes to Download",Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                        Toast.makeText(getApplicationContext(),exception.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}