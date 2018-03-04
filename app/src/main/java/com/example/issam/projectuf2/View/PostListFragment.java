package com.example.issam.projectuf2.View;


import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.issam.projectuf2.Model.Post;
import com.example.issam.projectuf2.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public abstract class PostListFragment extends Fragment {
    DatabaseReference mReference;
    FirebaseRecyclerAdapter mAdapter;

    public PostListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_post_list, container, false);
        mReference = FirebaseDatabase.getInstance().getReference();

        RecyclerView recyclerView = view.findViewById(R.id.post_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Post>()
                .setIndexedQuery(setQuery(), mReference.child("posts/data"), Post.class)
                .setLifecycleOwner(this)
                .build();

        mAdapter = new FirebaseRecyclerAdapter<Post, PostViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PostViewHolder holder, final int position,final @NonNull Post post) {
                holder.author.setText(post.author);
                Glide.with(PostListFragment.this)
                        .load(post.authorPicUrl)
                        .apply(new RequestOptions().circleCrop())
                        .into(holder.aurthorPhoto);

                holder.content.setText(post.content);
                holder.image.setVisibility(View.VISIBLE);
                if(post.mediaUrl != null) {
                    if("audio".equals(post.mediaType)){
                        holder.image.setImageResource(R.drawable.audio);
                    } else {
                        Glide.with(PostListFragment.this)
                                .load(post.mediaUrl)
                                .into(holder.image);
                    }
                    holder.image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getActivity(), MediaActivity.class);
                            intent.putExtra("MEDIA_URL", post.mediaUrl);
                            intent.putExtra("MEDIA_TYPE", post.mediaType);
                            startActivity(intent);
                        }
                    });

                } else {
                    holder.image.setVisibility(View.GONE);
                }
                if(post.likes != null && post.likes.containsKey(FirebaseAuth.getInstance().getUid())){
                    holder.like.setImageResource(R.drawable.heart_on);
                } else{
                    holder.like.setImageResource(R.drawable.heart_off);

                }

                holder.likeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String uid = FirebaseAuth.getInstance().getUid();
                        String postKey = getRef(position).getKey();
                        if(post.likes != null && post.likes.containsKey(uid)){
                            mReference.child("posts/data").child(getRef(position).getKey()).child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(null);
                            mReference.child("posts/user-likes").child(uid).child(postKey).setValue(null);
                        } else{
                            mReference.child("posts/data").child(getRef(position).getKey()).child("likes").child(FirebaseAuth.getInstance().getUid()).setValue(true);
                            mReference.child("posts/user-likes").child(uid).child(postKey).setValue(true);

                        }
                    }
                });


            }

            @Override
            public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_post_b, parent, false);
                return new PostViewHolder(view);
            }
        };

        recyclerView.setAdapter(mAdapter);

        return view;
    }
    abstract Query setQuery();

}
