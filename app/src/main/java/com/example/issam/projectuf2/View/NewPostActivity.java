package com.example.issam.projectuf2.View;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.issam.projectuf2.Model.Post;
import com.example.issam.projectuf2.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class NewPostActivity extends AppCompatActivity{
    Button btnPublish, btnPhoto, btnVideo, btnAudio;
    DatabaseReference databaseReference;
    EditText content;
    ImageView image;
    Uri mediUri;
    Uri downloadUrl;
    String mediaType;

    int RC_IMAGE_PICK = 9000;
    int RC_VIDEO_PICK = 9001;
    int RC_AUDIO_PICK = 9002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);

        databaseReference = FirebaseDatabase.getInstance().getReference();

         content = findViewById(R.id.content);
         image = findViewById(R.id.image);
         btnPhoto = findViewById(R.id.btnImage);
         btnVideo = findViewById(R.id.btnVideo);
         btnAudio = findViewById(R.id.btnAudio);
         btnPublish = findViewById(R.id.publish);

        btnPublish.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                btnPublish.setEnabled(false);
                if(mediUri != null){
                    uploadFileAndWriteNewPost();
                }else {
                    writeNewPost();
                }
                finish();
            }
        });

        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RC_IMAGE_PICK);
            }
        });

        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RC_VIDEO_PICK);
            }
        });

        btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, RC_AUDIO_PICK);
            }
        });

    }
    void writeNewPost(){
        String postKey = databaseReference.child("posts").push().getKey();
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        Post post;

        if(mediUri == null){
            post = new Post(firebaseUser.getUid(), content.getText().toString(),firebaseUser.getDisplayName(),firebaseUser.getPhotoUrl().toString());
        }else {
           post = new Post(firebaseUser.getUid(), content.getText().toString(),firebaseUser.getDisplayName(),firebaseUser.getPhotoUrl().toString(), downloadUrl.toString(), mediaType);
        }
        databaseReference.child("posts/data").child(postKey).setValue(post);
        databaseReference.child("posts/all-posts").child(postKey).setValue(true);
        databaseReference.child("posts/user-posts").child(FirebaseAuth.getInstance().getUid()).child(postKey).setValue(true);
    }

    void uploadFileAndWriteNewPost(){
        StorageReference fileRef = FirebaseStorage.getInstance().getReference().child(mediaType + "/" + UUID.randomUUID() + "-" + mediUri.getLastPathSegment());

        Log.i(".....", "uploadFileAndWriteNewPost: "+fileRef);
        fileRef.putFile(mediUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                downloadUrl = taskSnapshot.getDownloadUrl();

                writeNewPost();
            }
        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data != null) {
            if(requestCode == RC_IMAGE_PICK){
                mediUri = data.getData();
                mediaType = "image";
                Glide.with(this).load(mediUri).into(image);

            }else if (requestCode == RC_VIDEO_PICK){
                mediUri = data.getData();
                mediaType = "video";
                Glide.with(this).load(mediUri).into(image);

            }else if(requestCode == RC_AUDIO_PICK){
                mediaType = "audio";
                image.setImageResource(R.drawable.audio);

            }
        }
    }


}
