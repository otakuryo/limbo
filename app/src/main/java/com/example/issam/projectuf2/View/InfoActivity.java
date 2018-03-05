package com.example.issam.projectuf2.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.issam.projectuf2.Model.Post;
import com.example.issam.projectuf2.R;

public class InfoActivity extends AppCompatActivity {
    Post post;
    CollapsingToolbarLayout collapsingToolbarLayout;

    TextView author, fIni,fEnd,vYou;
    ImageView aurthorPhoto;
    TextView content;
    ImageView image;
    ImageView like;
    TextView likeCount;
    LinearLayout likeLayout;
    ImageView imageView;
    ImageView imageBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //busqueda de campos
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout);
        //author = findViewById(R.id.info_author);
        //aurthorPhoto = findViewById(R.id.info_photo);
        content = findViewById(R.id.info_content);
        like = findViewById(R.id.info_like);
        likeCount = findViewById(R.id.info_num_likes);
        //image = findViewById(R.id.info_image);

        fIni=findViewById(R.id.info_fIni);
        fEnd=findViewById(R.id.info_fEnd);
        vYou=findViewById(R.id.info_vYou);
        imageView= findViewById(R.id.info_imageView);
        imageBack= findViewById(R.id.info_imageBack);
        //campos

        //se recogen los datos del post
        post=PostListFragment.getPostTempSelect();
        //fin de recogida

        collapsingToolbarLayout.setTitle(post.titulo);

        Intent intent = getIntent();
        //post=intent.getParcelableExtra("post");
        String mediaType = intent.getStringExtra("MEDIA_TYPE");
        String mediaUrl = intent.getStringExtra("MEDIA_URL");

        if(intent != null){

            if("image".equals(mediaType)){
                Glide.with(this).load(post.mediaUrl).into(imageView);
                Glide.with(this).load(post.mediaUrl).into(imageBack);
                content.setText(post.content);
                fIni.setText(post.fIni);
                fEnd.setText(post.fEnd);
                vYou.setText(post.vYou);
            } else{
                VideoView videoView = findViewById(R.id.videoView);
                MediaController mc = new MediaController(this);
                mc.setAnchorView(videoView);
                videoView.setMediaController(mc);
                videoView.setVideoPath(mediaUrl);
                videoView.start();
            }
        }


        FloatingActionButton fab = findViewById(R.id.info_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMaps();
                Snackbar.make(view, "Se abrira google maps con x ubicacion", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    void googleMaps(){
        String lat= "41.45572457111545";
        String lon="2.201298946723341";
        String text="IES Puig Lol";

        // Create a Uri from an intent string. Use the result to create an Intent.
        Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lon+"?q="+lat+","+lon+"("+text+")");

        // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

        // Make the Intent explicit by setting the Google Maps package
        mapIntent.setPackage("com.google.android.apps.maps");

        // Attempt to start an activity that can handle the Intent
        startActivity(mapIntent);
    }
}
