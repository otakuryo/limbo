package com.example.issam.projectuf2.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.issam.projectuf2.R;


public class MediaActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_media);

        Intent intent = getIntent();

        if(intent != null){
            String mediaType = intent.getStringExtra("MEDIA_TYPE");
            String mediaUrl = intent.getStringExtra("MEDIA_URL");

            if("image".equals(mediaType)){
                ImageView imageView = findViewById(R.id.imageView);
                Glide.with(this).load(mediaUrl).into(imageView);
            } else{
                VideoView videoView = findViewById(R.id.videoView);
                MediaController mc = new MediaController(this);
                mc.setAnchorView(videoView);
                videoView.setMediaController(mc);
                videoView.setVideoPath(mediaUrl);
                videoView.start();
            }
        }
    }

}
