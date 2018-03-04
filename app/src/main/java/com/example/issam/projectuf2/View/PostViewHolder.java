package com.example.issam.projectuf2.View;


import android.speech.tts.TextToSpeech;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.issam.projectuf2.R;

import org.w3c.dom.Text;

public class PostViewHolder extends RecyclerView.ViewHolder{

    TextView author;
    ImageView aurthorPhoto;
    TextView content;
    ImageView image;
    ImageView like;
    TextView likeCount;
    LinearLayout likeLayout;
    public PostViewHolder(View itemView) {
        super(itemView);
        author = itemView.findViewById(R.id.author);
        aurthorPhoto = itemView.findViewById(R.id.photo);
        content = itemView.findViewById(R.id.content);
        like = itemView.findViewById(R.id.like);
        likeCount = itemView.findViewById(R.id.num_likes);
        likeLayout = itemView.findViewById(R.id.like_layout);
        image = itemView.findViewById(R.id.image);

    }
}
